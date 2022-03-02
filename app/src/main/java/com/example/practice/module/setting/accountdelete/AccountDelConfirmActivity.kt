package com.example.practice.module.setting.accountdelete

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.practice.R
import com.example.practice.base.BaseActivity
import com.example.practice.bean.AccountDelRequestBean
import com.example.practice.databinding.ActivityAccountDelConfirmBinding
import com.example.practice.utils.LoadingDialogUtils

class AccountDelConfirmActivity : BaseActivity<ActivityAccountDelConfirmBinding>(ActivityAccountDelConfirmBinding::inflate) {
    private lateinit var accountDelViewModel:AccountDelViewModel
    private val requestBody = AccountDelRequestBean("123456")
    private var mLoadingDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initView()
    }

    private fun initData(){
        accountDelViewModel = ViewModelProvider(this)[AccountDelViewModel::class.java]

    }

    private fun initView(){
        val titleBack = viewBinding.titleAccountDel.titleBtn
        val delBtn = viewBinding.delButton
        var loadingDialog = LoadingDialogUtils()
        //タイトルの戻るボタンを設定
        titleBack.visibility = View.VISIBLE
        titleBack.setImageResource(R.mipmap.white_back)
        titleBack.setOnClickListener {
            finish()
        }
        delBtn.setOnClickListener {
            accountDelViewModel.requestAccountDel(requestBody)
        }
        accountDelViewModel.loadingLiveData.observe(this,{
            if(it){
                mLoadingDialog = loadingDialog.createLoadingDialog(this,"Loading")
            } else {
                loadingDialog.closeDialog(mLoadingDialog)
                val intent = Intent(this,AccountDelDoneActivity().javaClass)
                startActivity(intent)
                Toast.makeText(this, accountDelViewModel.accountDelLiveData.value?.getOrNull()?.accountDelInfo?.msg, Toast.LENGTH_LONG).show()
            }
        })
    }

}