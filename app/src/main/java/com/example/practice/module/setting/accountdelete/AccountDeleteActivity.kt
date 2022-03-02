package com.example.practice.module.setting.accountdelete

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.practice.R
import com.example.practice.base.BaseActivity
import com.example.practice.databinding.ActivityAccountDeleteBinding


class AccountDeleteActivity : BaseActivity<ActivityAccountDeleteBinding>(ActivityAccountDeleteBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView(){
        val titleBack = viewBinding.titleAccountDel.titleBtn
        val confirmBtn = viewBinding.confirmButton
        //タイトルの戻るボタンを設定
        titleBack.visibility = View.VISIBLE
        titleBack.setImageResource(R.mipmap.white_back)
        titleBack.setOnClickListener {
            finish()
        }
        confirmBtn.setOnClickListener {
            val intent = Intent(this,AccountDelConfirmActivity().javaClass)
            startActivity(intent)
        }
    }
}