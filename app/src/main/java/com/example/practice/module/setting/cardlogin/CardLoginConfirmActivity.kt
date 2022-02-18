package com.example.practice.module.setting.cardlogin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.practice.R
import com.example.practice.base.BaseActivity
import com.example.practice.bean.CardLoginRequestBean
import com.example.practice.databinding.ActivityCardLoginConfirmBinding
import com.example.practice.module.pay.payment.PayCompletedActivity
import com.example.practice.module.setting.SettingsFragment
import com.example.practice.utils.LoadingDialogUtils

class CardLoginConfirmActivity : BaseActivity<ActivityCardLoginConfirmBinding>(ActivityCardLoginConfirmBinding::inflate) {

    private val cardViewModel: CardLoginViewModel by viewModels()
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

        confirmTv.setOnClickListener {
            cardViewModel.cardNumberLogin(requestData)
        }

        cardViewModel.loadingLiveData.observe(this,  {
            if(it){
                mLoadingDialog = loadingDialog.createLoadingDialog(this,"Loading")
            } else {
                loadingDialog.closeDialog(mLoadingDialog)
                Toast.makeText(this, "カード番号を登録しました！", Toast.LENGTH_LONG).show()
            }
        })
    }
}