package com.example.practice.module.setting.notification


import android.app.Dialog
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.practice.R
import com.example.practice.base.BaseActivity
import com.example.practice.databinding.ActivityNotificationModifyBinding
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan
import android.widget.Toast
import com.example.practice.utils.LoadingDialogUtils


class NotificationModifyActivity : BaseActivity<ActivityNotificationModifyBinding>(
    ActivityNotificationModifyBinding::inflate)  {

    private lateinit var messageViewModel:NotificationModifyViewModel
    private var mLoadingDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initView()
    }

    private fun initData(){
        messageViewModel = ViewModelProvider(this)[NotificationModifyViewModel::class.java]
        messageViewModel.getMessageEditData()
    }

    private fun initView(){
        val titleBackBtn = viewBinding.titleChargeChange.titleBtn
        val msgEdit = viewBinding.notificationEdit
        val changeBtn = viewBinding.confirmButton
        var loadingDialog = LoadingDialogUtils()
        //タイトルの戻るボタンを設定
        titleBackBtn.visibility = View.VISIBLE
        titleBackBtn.setImageResource(R.mipmap.white_back)
        titleBackBtn.setOnClickListener {
            finish()
        }
        messageViewModel.loadingLiveData.observe(this,{
            if(it){
                mLoadingDialog = loadingDialog.createLoadingDialog(this,"Loading")
            } else {
                loadingDialog.closeDialog(mLoadingDialog)
            }
            messageViewModel.messageLiveData.observe(this,{
                val messageText = SpannableString(it.getOrNull()?.messageData?.message_title+"\n"+it.getOrNull()?.messageData?.message_body)
                messageText.setSpan(AbsoluteSizeSpan(23,true),0,it.getOrNull()?.messageData?.message_title!!.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                messageText.setSpan(StyleSpan(Typeface.BOLD),0,it.getOrNull()?.messageData?.message_title!!.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                msgEdit.setText(messageText)
            })

            changeBtn.setOnClickListener{
                val bundle = Bundle()
                bundle.putString("msg",msgEdit.text.toString())
                val intent = Intent(this,NotificationEditMessageActivity().javaClass)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })
    }
}