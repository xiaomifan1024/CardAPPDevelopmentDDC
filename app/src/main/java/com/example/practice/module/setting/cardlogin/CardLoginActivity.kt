package com.example.practice.module.setting.cardlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.practice.R
import com.example.practice.base.BaseActivity
import com.example.practice.databinding.ActivityCardLoginBinding
import com.example.practice.module.setting.SettingsFragment

class CardLoginActivity : BaseActivity<ActivityCardLoginBinding>(ActivityCardLoginBinding::inflate)  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        val titleBackBtn = viewBinding.titleCardLogin.titleBtn
        val confirmBtn = viewBinding.confirmButton
        val cardNumEdt = viewBinding.number
        val dateEdt = viewBinding.date
        val cardNameEdt = viewBinding.cardName
        val securityEdt = viewBinding.securityCode
        //タイトルの戻るボタンを設定
        titleBackBtn.visibility = View.VISIBLE
        titleBackBtn.setImageResource(R.mipmap.white_back)
        titleBackBtn.setOnClickListener {
            finish()
        }
        //登録確認画面
        confirmBtn.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("card_num", cardNumEdt.text.toString())
            bundle.putString("date", dateEdt.text.toString())
            bundle.putString("card_name", cardNameEdt.text.toString())
            bundle.putString("security_code", securityEdt.text.toString())
            val intent = Intent(this, CardLoginConfirmActivity().javaClass)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}