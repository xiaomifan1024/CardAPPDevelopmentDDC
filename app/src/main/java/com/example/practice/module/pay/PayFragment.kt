package com.example.practice.module.pay

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.practice.base.BaseFragment
import com.example.practice.databinding.FragmentPayBinding
import android.view.animation.LinearInterpolator
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.practice.R
import com.example.practice.utils.QRCodeUtil
import java.io.File

class PayFragment : BaseFragment<FragmentPayBinding>(FragmentPayBinding::inflate),View.OnClickListener {

    private lateinit var payViewModel: PayViewModel
    private lateinit var filePath:String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }
    private fun initData() {
        filePath=(context?.filesDir?.absolutePath.toString() + File.separator
                + "qr_" + System.currentTimeMillis() + ".jpg")
        payViewModel =
            ViewModelProvider(this).get(PayViewModel::class.java)
    }

    private fun initView() {
        val imageView: ImageView = viewBinding.textPay
        val gifView: ImageView = viewBinding.refresh
        var isCreated =createQRCode()
        payViewModel.text.observe(viewLifecycleOwner, Observer {
            if(isCreated) {
                val resultBitmap = BitmapFactory.decodeFile(filePath)
                imageView.setImageBitmap(resultBitmap)
            }else {
                Toast.makeText(context, "QRCodeを作成できません" , Toast.LENGTH_SHORT).show()
                imageView.setImageResource(R.mipmap.failed)
            }
        })
        gifView.setOnClickListener(this)

    }
    override fun onClick(p0: View?) {
        when (p0) {
            is ImageView->{
                var myAlphaAnimation = AnimationUtils.loadAnimation(context, R.anim.refresh)
                myAlphaAnimation.interpolator = LinearInterpolator()
                this.viewBinding.refresh.startAnimation(myAlphaAnimation)
                Thread {
                    Thread.sleep(1000)
                    var success =createQRCode()
                    if (success) {
                        getActivity()?.runOnUiThread {
                            val resultBitmap = BitmapFactory.decodeFile(filePath)
                            this.viewBinding.textPay.setImageBitmap(resultBitmap)
                            this.viewBinding.refresh.clearAnimation()
                        }
                    }
                }.start()
            }
        }
    }

    private fun createQRCode(): Boolean {
        var qrCode = QRCodeUtil()
        return qrCode.createQRImage(
            "https://payapp.weixin.qq.com/materialqr/entry/home?id=065452545384977"+System.currentTimeMillis(), 300, 300,
            null, filePath
        )
    }

}