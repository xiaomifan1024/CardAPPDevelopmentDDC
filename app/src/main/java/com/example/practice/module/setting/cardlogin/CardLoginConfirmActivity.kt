package com.example.practice.module.setting.cardlogin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.room.Room
import com.example.practice.R
import com.example.practice.base.BaseActivity
import com.example.practice.bean.CardLoginRequestBean
import com.example.practice.databinding.ActivityCardLoginConfirmBinding
import com.example.practice.module.MainActivity
import com.example.practice.module.pay.payment.PayCompletedActivity
import com.example.practice.module.setting.SettingsFragment
import com.example.practice.room.MyDataBase
import com.example.practice.room.data.CardNumLogin
import com.example.practice.utils.LoadingDialogUtils
import kotlinx.coroutines.MainScope

class CardLoginConfirmActivity : BaseActivity<ActivityCardLoginConfirmBinding>(ActivityCardLoginConfirmBinding::inflate) {

    private lateinit var cardViewModel: CardLoginViewModel
    private val requestData = CardLoginRequestBean("1234567890123456","02/22",
        "TARO TANAKA ", 123)
    private var mLoadingDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView(){
        val titleBackBtn = viewBinding.titleCardLogin.titleBtn
        val confirmTv = viewBinding.loginButton
        val cardNumTv = viewBinding.number
        val dateTv = viewBinding.date
        val cardNameTv = viewBinding.cardName
        val securityTv = viewBinding.securityCode
        var loadingDialog = LoadingDialogUtils()
        val db = Room.databaseBuilder(applicationContext, MyDataBase::class.java,"myCardLogin.db").allowMainThreadQueries().build()
        cardViewModel = CardLoginViewModel(db)
        //タイトルの戻るボタンを設定
        titleBackBtn.visibility = View.VISIBLE
        titleBackBtn.setImageResource(R.mipmap.white_back)
        titleBackBtn.setOnClickListener {
            finish()
        }
        var bundle = intent.extras
        cardNumTv.text = bundle?.getString("card_num")
        dateTv.text = bundle?.getString("date")
        cardNameTv.text = bundle?.getString("card_name")
        securityTv.text = bundle?.getString("security_code")
        var cardData = bundle?.getString("card_num")?.let {
            CardNumLogin(
                it,
                bundle.getString("date")!!,
                bundle.getString("card_name")!!,
                bundle.getString("security_code")!!
            )
        }
        confirmTv.setOnClickListener {
            cardViewModel.cardNumberLogin(requestData)
            if (cardData != null) {
                cardViewModel.insertCardData(cardData)
            }
        }

        cardViewModel.loadingLiveData.observe(this,  {
            if(it){
                mLoadingDialog = loadingDialog.createLoadingDialog(this,"Loading")
            } else {
                loadingDialog.closeDialog(mLoadingDialog)
                val intent = Intent(this, MainActivity().javaClass)
                startActivity(intent)
                Toast.makeText(this, "カード番号を登録しました！", Toast.LENGTH_LONG).show()
            }
        })
    }
}