package com.example.practice.module.setting.accountdelete


import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.practice.R
import com.example.practice.base.BaseActivity
import com.example.practice.databinding.ActivityAccountDelDoneBinding
import com.example.practice.module.MainActivity


class AccountDelDoneActivity : BaseActivity<ActivityAccountDelDoneBinding>(
    ActivityAccountDelDoneBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView(){
        val titleBack = viewBinding.titleAccountDel.titleBtn
        val returnBtn = viewBinding.returnBtn
        //タイトルの戻るボタンを設定
        titleBack.visibility = View.VISIBLE
        titleBack.setImageResource(R.mipmap.white_back)
        titleBack.setOnClickListener {
            finish()
        }
        returnBtn.setOnClickListener {
            val intent = Intent(this,MainActivity().javaClass)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }

}