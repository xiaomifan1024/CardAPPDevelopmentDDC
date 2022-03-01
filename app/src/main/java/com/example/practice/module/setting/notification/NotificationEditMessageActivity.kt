package com.example.practice.module.setting.notification

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan
import android.view.View
import com.example.practice.R
import com.example.practice.base.BaseActivity
import com.example.practice.databinding.ActivityNotificationEditMessageBinding


class NotificationEditMessageActivity : BaseActivity<ActivityNotificationEditMessageBinding>(ActivityNotificationEditMessageBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView(){
        val titleBackBtn = viewBinding.titleChargeChange.titleBtn
        val editMsg = viewBinding.notificationEdit
        val confirmBtn = viewBinding.confirmButton
        //タイトルの戻るボタンを設定
        titleBackBtn.visibility = View.VISIBLE
        titleBackBtn.setImageResource(R.mipmap.white_back)
        titleBackBtn.setOnClickListener {
            finish()
        }
        val bundle = intent.extras
        val message = bundle?.getString("msg")?.split("\n")
        val messageText = SpannableString(message?.get(0)+"\n"+message?.get(1))
        messageText.setSpan(
            AbsoluteSizeSpan(23,true),0,message?.get(0)!!.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        messageText.setSpan(
            StyleSpan(Typeface.BOLD),0, message[0].length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        editMsg.setText(messageText)
        editMsg.requestFocus()

        confirmBtn.setOnClickListener {
            val bundle1 = Bundle()
            bundle1.putString("message",editMsg.text.toString())
            val intent = Intent(this,NotificationEditConfirmActivity().javaClass)
            intent.putExtras(bundle1)
            startActivity(intent)
        }


    }
}