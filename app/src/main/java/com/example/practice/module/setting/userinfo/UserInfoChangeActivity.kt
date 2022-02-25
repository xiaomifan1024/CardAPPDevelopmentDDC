package com.example.practice.module.setting.userinfo

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.practice.R
import com.example.practice.base.BaseActivity
import com.example.practice.databinding.ActivityUserInfoChangeBinding
import com.example.practice.utils.LoadingDialogUtils


class UserInfoChangeActivity : BaseActivity<ActivityUserInfoChangeBinding>(ActivityUserInfoChangeBinding::inflate)   {
    private lateinit var userInfoViewModel: UserInfoChangeViewModel
    private var mLoadingDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initView()
    }

    private fun initData() {
        userInfoViewModel = ViewModelProvider(this)[UserInfoChangeViewModel::class.java]
        userInfoViewModel.getUserInfoData()
    }

    private fun initView() {
        val firstName = viewBinding.firstName
        val lastName = viewBinding.lastName
        val birthDayYear = viewBinding.year
        val birthDayMonth = viewBinding.month
        val birthDayDay = viewBinding.day
        val zipCode = viewBinding.postNum
        val zipCode1 = viewBinding.postNum1
        val email = viewBinding.mail
        val password = viewBinding.password
        val password1 = viewBinding.password1
        val titleBack = viewBinding.titleUserInfo.titleBtn
        var loadingDialog = LoadingDialogUtils()

        //タイトルの戻るボタンを設定
        titleBack.visibility = View.VISIBLE
        titleBack.setImageResource(R.mipmap.white_back)
        titleBack.setOnClickListener {
            finish()
        }

        userInfoViewModel.userInfoLiveData.observe(this,{
            firstName.setText(it.getOrNull()?.customerData?.etName1)
            lastName.setText(it.getOrNull()?.customerData?.etName2)
            birthDayYear.setText(it.getOrNull()?.customerData?.etBirthday?.substring(0,4))
            birthDayMonth.setText(it.getOrNull()?.customerData?.etBirthday?.substring(5,7))
            birthDayDay.setText(it.getOrNull()?.customerData?.etBirthday?.substring(8,10))
            zipCode.setText(it.getOrNull()?.customerData?.eZipCode?.substring(0,4))
            zipCode1.setText(it.getOrNull()?.customerData?.eZipCode?.substring(4,7))
            email.setText(it.getOrNull()?.customerData?.etEmail)
            password.setText(it.getOrNull()?.customerData?.etPassword1)
            password1.setText(it.getOrNull()?.customerData?.etPassword1)
        })
        userInfoViewModel.loadingLiveData.observe(this ,{
            if(it){
                mLoadingDialog = loadingDialog.createLoadingDialog(this,"Loading")
            } else {
                loadingDialog.closeDialog(mLoadingDialog)
            }

        })

    }
}