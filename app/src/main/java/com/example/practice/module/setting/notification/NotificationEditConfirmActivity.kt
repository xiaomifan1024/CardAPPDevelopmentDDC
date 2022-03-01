package com.example.practice.module.setting.notification

import android.app.Dialog
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.practice.R
import com.example.practice.base.BaseActivity
import com.example.practice.bean.NotificationEditRequestBean
import com.example.practice.databinding.ActivityNotificationEditConfirmBinding
import com.example.practice.utils.LoadingDialogUtils

class NotificationEditConfirmActivity : BaseActivity<ActivityNotificationEditConfirmBinding>(
    ActivityNotificationEditConfirmBinding::inflate) {

    private lateinit var messageViewModel:NotificationModifyViewModel
    private var mLoadingDialog: Dialog? = null
    private val requestData = NotificationEditRequestBean("ルミネ新宿店\n" +
            "商品のご購入ありがとうございました。\n" +
            "12/1～○○セール開催")

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initView()
    }

    private fun initData(){
        messageViewModel = ViewModelProvider(this)[NotificationModifyViewModel::class.java]
    }

    private fun initView(){
        val titleBackBtn = viewBinding.titleChargeChange.titleBtn
        val textMsg = viewBinding.notificationEdit
        val confirmBtn = viewBinding.loginBtn
        var loadingDialog = LoadingDialogUtils()
        //タイトルの戻るボタンを設定
        titleBackBtn.visibility = View.VISIBLE
        titleBackBtn.setImageResource(R.mipmap.white_back)
        titleBackBtn.setOnClickListener {
            finish()
        }
        val bundle = intent.extras
        val message = bundle?.getString("message")?.split("\n")
        val messageText = SpannableString(message?.get(0)+"\n"+message?.get(1))
        messageText.setSpan(
            AbsoluteSizeSpan(23,true),0,message?.get(0)!!.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        messageText.setSpan(
            StyleSpan(Typeface.BOLD),0, message[0].length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textMsg.text = messageText

        confirmBtn.setOnClickListener {
            messageViewModel.messageEditRequest(requestData)
        }
        messageViewModel.loadingLiveData.observe(this,{
            if(it){
                mLoadingDialog = loadingDialog.createLoadingDialog(this,"Loading")
            } else {
                loadingDialog.closeDialog(mLoadingDialog)
                Toast.makeText(this, messageViewModel.editLiveData.value?.getOrNull()?.messageData?.msg, Toast.LENGTH_LONG).show()
            }
        })
    }
}