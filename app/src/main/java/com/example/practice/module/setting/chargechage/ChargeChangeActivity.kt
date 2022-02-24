package com.example.practice.module.setting.chargechage

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.practice.R
import com.example.practice.base.BaseActivity
import com.example.practice.bean.CurrentLimitRequestBean
import com.example.practice.databinding.ActivityChargeChangeBinding
import com.example.practice.utils.LoadingDialogUtils


class ChargeChangeActivity : BaseActivity<ActivityChargeChangeBinding>(ActivityChargeChangeBinding::inflate)  {
    private lateinit var chargeViewModel: ChargeChangeViewModel
    private var mLoadingDialog: Dialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initView()
    }

    private fun initData() {
        chargeViewModel = ViewModelProvider(this)[ChargeChangeViewModel::class.java]
        chargeViewModel.getChargeLimitData()
    }

    private fun initView(){
        val titleBackBtn = viewBinding.titleChargeChange.titleBtn
        val chargeTv = viewBinding.charge
        val confirmBtn = viewBinding.confirmButton
        val changeEdt = viewBinding.chargeChange
        var loadingDialog = LoadingDialogUtils()
        //タイトルの戻るボタンを設定
        titleBackBtn.visibility = View.VISIBLE
        titleBackBtn.setImageResource(R.mipmap.white_back)
        titleBackBtn.setOnClickListener {
            finish()
        }
        chargeViewModel.limitLiveData.observe(this,{
            if(it.getOrNull()?.currentLimitData==null){
                return@observe
            } else {
                chargeTv.text = it.getOrNull()?.currentLimitData?.currentLimit.toString() + "円"
            }
        })

        confirmBtn.setOnClickListener {
            val requestData = CurrentLimitRequestBean(50000)
            chargeViewModel.chargeLimitDataRequest(requestData)
            chargeViewModel.loadingLiveData.observe(this,  {
                if(it){
                    mLoadingDialog = loadingDialog.createLoadingDialog(this,"Loading")
                } else {
                    loadingDialog.closeDialog(mLoadingDialog)
                    Toast.makeText(this, chargeViewModel.changeLimitLiveData.value?.getOrNull()?.changeData?.msg, Toast.LENGTH_LONG).show()
                    finish()
                }
            })
        }

    }
}