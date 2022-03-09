package com.example.practice.module.setting.userinfo


import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.practice.R
import com.example.practice.base.BaseActivity
import com.example.practice.bean.UserInfoRequest
import com.example.practice.databinding.ActivityUserInfoChangeConfirmBinding
import com.example.practice.module.MainActivity
import com.example.practice.utils.LoadingDialogUtils


class UserInfoChangeConfirmActivity : BaseActivity<ActivityUserInfoChangeConfirmBinding>(
    ActivityUserInfoChangeConfirmBinding::inflate) {

    private lateinit var userInfoViewModel: UserInfoChangeViewModel
    private var mLoadingDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initView()
    }

    private fun initView(){
        val firstName = viewBinding.firstName
        val lastName = viewBinding.lastName
        val year = viewBinding.year
        val month = viewBinding.month
        val day = viewBinding.day
        val zipcode = viewBinding.postNum
        val zipcode1 = viewBinding.postNum1
        val address = viewBinding.address
        val email = viewBinding.mail
        val password = viewBinding.password
        val titleBack = viewBinding.titleUserInfo.titleBtn
        val changeBtn = viewBinding.confirmButton
        val sexTv = viewBinding.sex
        var loadingDialog = LoadingDialogUtils()
        //タイトルの戻るボタンを設定
        titleBack.visibility = View.VISIBLE
        titleBack.setImageResource(R.mipmap.white_back)
        titleBack.setOnClickListener {
            finish()
        }
        val bundle = intent.extras
        firstName.text = bundle?.getString("firstName")
        lastName.text = bundle?.getString("lastName")
        year.text = bundle?.getString("year")
        month.text = bundle?.getString("month")
        day.text = bundle?.getString("day")
        zipcode.text = bundle?.getString("zipCode")
        zipcode1.text = bundle?.getString("zipCode1")
        email.text = bundle?.getString("email")
        password.text = bundle?.getString("password")
        sexTv.text = bundle?.getString("gender")
        changeBtn.setOnClickListener {
            val requestBean = UserInfoRequest(firstName.text.toString(),lastName.text.toString(),"1990/01/01","1111234",email.text.toString(),password.text.toString())
            userInfoViewModel.requestUserInfoChange(requestBean)
        }
        userInfoViewModel.loadingLiveData.observe(this ,{ it ->
            if(it){
                mLoadingDialog = loadingDialog.createLoadingDialog(this,"Loading")
            } else {
                userInfoViewModel.userChangeLiveData.observe(this,{
                    Toast.makeText(this, it.getOrNull()?.userInfoData?.msg, Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity().javaClass)
                    startActivity(intent)
                })
                loadingDialog.closeDialog(mLoadingDialog)
            }

        })

    }

    private fun initData() {
        userInfoViewModel = ViewModelProvider(this)[UserInfoChangeViewModel::class.java]
    }
}