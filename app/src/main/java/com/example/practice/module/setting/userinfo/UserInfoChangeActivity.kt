package com.example.practice.module.setting.userinfo

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.practice.R
import com.example.practice.base.BaseActivity
import com.example.practice.databinding.ActivityUserInfoChangeBinding
import com.example.practice.utils.LoadingDialogUtils
import android.view.ViewGroup
import com.example.practice.constant.DateSelectType
import com.example.practice.utils.DatePickerView
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker





class UserInfoChangeActivity : BaseActivity<ActivityUserInfoChangeBinding>(ActivityUserInfoChangeBinding::inflate)   {
    private lateinit var userInfoViewModel: UserInfoChangeViewModel
    private var mLoadingDialog: Dialog? = null
    private var isSpinnerFirst: Boolean = true
    private var selectedView: View? = null

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
        val confirmBtn = viewBinding.confirmButton
        val sexSpinner = viewBinding.sex
        val address = viewBinding.address
        var loadingDialog = LoadingDialogUtils()
        var dataPicker = DatePickerView(this)
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

        //情報変更確認へ遷移
        confirmBtn.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("firstName", firstName.text.toString())
            bundle.putString("lastName", lastName.text.toString())
            bundle.putString("year",birthDayYear.text.toString())
            bundle.putString("month",birthDayMonth.text.toString())
            bundle.putString("day",birthDayDay.text.toString())
            bundle.putString("zipCode",zipCode.text.toString())
            bundle.putString("zipCode1",zipCode1.text.toString())
            bundle.putString("address",address.text.toString())
            bundle.putString("email",email.text.toString())
            bundle.putString("password",password.text.toString())
            bundle.putString("passwordCheck",password1.text.toString())
            bundle.putString("gender",sexSpinner.selectedItem.toString())
            val intent = Intent(this,UserInfoChangeConfirmActivity().javaClass)
            intent.putExtras(bundle)
            startActivity(intent)
        }
        val sexArr = resources.getStringArray(R.array.sex)
        sexSpinner.onItemSelectedListener =  object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                userInfoViewModel.isSpinnerShow.observe(this@UserInfoChangeActivity,{ it ->
                    if(it) {
                        view?.visibility = View.INVISIBLE
                    } else {
                        view?.visibility = View.VISIBLE
                        if(isSpinnerFirst) {
                            userInfoViewModel.userInfoLiveData.observe(this@UserInfoChangeActivity, {
                                if(it.getOrNull()?.customerData?.eGender.equals("男性")) {
                                    sexSpinner.setSelection(0)
                                } else {
                                    sexSpinner.setSelection(1)
                                }
                            })
                        }
                    }
                })
                isSpinnerFirst = false
            }
        }

        val adapter = ArrayAdapter(this, R.layout.item_select, sexArr)
        adapter.setDropDownViewResource(R.layout.item_dropdown)
        sexSpinner.adapter = adapter
        birthDayYear.setOnClickListener {
            dataPicker.showSingleDatePickerDialog(birthDayYear, DateSelectType.YEAR)
        }
        birthDayMonth.setOnClickListener {
            dataPicker.showSingleDatePickerDialog(birthDayMonth, DateSelectType.MONTH)
        }
        birthDayDay.setOnClickListener {
            dataPicker.showSingleDatePickerDialog(birthDayDay, DateSelectType.DAY)
        }

    }
}