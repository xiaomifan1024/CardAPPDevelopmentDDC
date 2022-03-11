package com.example.practice.module.pay

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.example.practice.base.BaseFragment
import com.example.practice.databinding.FragmentPayBinding
import android.view.animation.LinearInterpolator
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.practice.utils.QRCodeUtil
import java.io.File
import android.os.CountDownTimer
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.practice.module.pay.charge.ChargeActivity
import com.example.practice.R
import com.example.practice.module.pay.payment.PaymentActivity
import com.google.zxing.integration.android.IntentIntegrator
import android.app.Activity
import android.content.Intent.getIntent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult


class PayFragment : BaseFragment<FragmentPayBinding>(FragmentPayBinding::inflate),View.OnClickListener {

    private lateinit var payViewModel: PayViewModel
    private lateinit var filePath:String //QRCodeキャッシュパス
    private val TOTAL_TIME = 300000L //5分カウントダウン
    private val ONECE_TIME = 1000L //1秒おきに
    private var activityResultLauncher: ActivityResultLauncher<Intent>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }
    private fun initData() {
        //QRCodeキャッシュパス
        filePath=(context?.filesDir?.absolutePath.toString() + File.separator
                + "qr_" + System.currentTimeMillis() + ".jpg")
        payViewModel =
            ViewModelProvider(this)[PayViewModel::class.java]
        payViewModel.getLeftMoneyData()
    }

    private fun initView() {
        val imageView: ImageView = viewBinding.refresh
        val chargeRl: RelativeLayout = viewBinding.chargeRl
        val payRl: RelativeLayout = viewBinding.payRl
        val titleView: TextView = viewBinding.titlePay.title
        val scanRl: RelativeLayout = viewBinding.scanRl
        val bundle = getActivity()?.intent?.extras
        val chargeMoney = bundle?.getString("charge")
        val payMoney = bundle?.getString("pay")
        //QRCodeを作成
        var isCreated =createQRCode()
        payViewModel.text.observe(viewLifecycleOwner, {
            //QRコードのイメージを更新
            setQRImageView(isCreated)
        })
        payViewModel.leftMoneyLiveData.observe(viewLifecycleOwner, {
            //残高を取得
            if(it.getOrNull() != null) {
                viewBinding.moneyLeft.text = it.getOrNull()?.leftData?.money.toString() + "円"
            }
        })
        imageView.setOnClickListener(this)
        countDownTimer.start()
        //チャージ画面へ遷移
        chargeRl.setOnClickListener {
            var intent = Intent(this.getActivity(), ChargeActivity().javaClass)
            startActivity(intent)
        }
        //支払う画面へ遷移
        payRl.setOnClickListener {
            var intent = Intent(this.getActivity(), PaymentActivity().javaClass)
            startActivity(intent)
        }
        titleView.text=resources.getString(R.string.title_pay)
        titleView.setTextColor(resources.getColor(R.color.white,null))
        //スキャニングQRCodeのregisterForActivityResult callback
        getScanResult()
        //スキャニングQRCode
        scanRl.setOnClickListener {
            val intentIntegrator = IntentIntegrator(getActivity())
            intentIntegrator.setPrompt("QRコードはファインダーボックス内でスキャンしてください")
            activityResultLauncher!!.launch(intentIntegrator.createScanIntent())

        }

    }
    override fun onClick(p0: View?) {
        when (p0) {
            is ImageView->{
                //更新動画
                var myAlphaAnimation = AnimationUtils.loadAnimation(context, R.anim.refresh)
                myAlphaAnimation.interpolator = LinearInterpolator()
                this.viewBinding.refresh.startAnimation(myAlphaAnimation)
                Thread {
                    Thread.sleep(1000)
                    var isSuccess =createQRCode()
                    getActivity()?.runOnUiThread {
                        //QRコードのイメージを更新
                        setQRImageView(isSuccess)
                        this.viewBinding.refresh.clearAnimation()
                    }
                }.start()
            }
        }
    }

    /**
     * CountDownTimer カウントダウンを実現
     */
    private val countDownTimer: CountDownTimer = object : CountDownTimer(TOTAL_TIME, ONECE_TIME) {
        override fun onTick(millisUntilFinished: Long) {
            val minute = millisUntilFinished / (1000*60)
            val second = (millisUntilFinished % (1000*60))/1000
            val timeS = if(second < 10){
                "0$second"
            }else {
                second.toString()
            }
            viewBinding.timeCount.text=minute.toString() + ":" + timeS +"後、QRコードを更新します。"
        }
        override fun onFinish() {
            //カウントダウン終了後にQRコードを自動に更新
            var isSuccess =createQRCode()
            setQRImageView(isSuccess)
        }
    }

    /**
     * QRコードを作成する
     */
    private fun createQRCode(): Boolean {
        var qrCode = QRCodeUtil()
        return qrCode.createQRImage(
            "https://payapp.weixin.qq.com/materialqr/entry/home?id=065452545384977"+System.currentTimeMillis(), 300, 300,
            null, filePath
        )
    }

    /**
     * QRコードイメージをセット
     */
    private fun setQRImageView(isSuccess:Boolean){
        if (isSuccess) {
            //QRコードの作成に成功したら表示
            val resultBitmap = BitmapFactory.decodeFile(filePath)
            this.viewBinding.textPay.setImageBitmap(resultBitmap)
            countDownTimer.start()
        }
        else {
            //QRコードの作成に失敗したら失敗アイコンを表示
            Toast.makeText(context, "QRCodeを作成できません" , Toast.LENGTH_SHORT).show()
            this.viewBinding.textPay.setImageResource(R.mipmap.failed)
        }
    }
    /**
     * QRコードのスキャニング結果を取得
     */
    private fun getScanResult(){
         activityResultLauncher = registerForActivityResult(
                StartActivityForResult()
                ) { result: ActivityResult ->
            val data = result.data
            when (result.resultCode) {
                Activity.RESULT_OK -> {
                    val resultScan = IntentIntegrator.parseActivityResult(result.resultCode, data)
                    if (result != null) {
                        if (resultScan.contents == null) {
                            Toast.makeText(getActivity(), "スキャニング失敗", Toast.LENGTH_LONG).show()
                        } else {
                            val bundle =Bundle()
                            bundle.putString("scan_contents",resultScan.contents)
                            var intent = Intent(
                                this.getActivity(), ScanQrResultActivity()::class.java
                            )
                            intent.putExtras(bundle)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }

}