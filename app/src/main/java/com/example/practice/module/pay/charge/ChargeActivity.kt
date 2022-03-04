package com.example.practice.module.pay.charge

import android.app.Dialog
import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import com.example.practice.R
import com.example.practice.base.BaseActivity
import com.example.practice.databinding.ActivityChargeBinding
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.provider.ContactsContract

import android.view.KeyEvent
import android.view.View
import android.widget.*
import android.widget.TextView.OnEditorActionListener
import androidx.lifecycle.ViewModelProvider
import com.example.practice.bean.AccountDelRequestBean
import com.example.practice.bean.ChargeDataRequestBean
import com.example.practice.module.setting.accountdelete.AccountDelDoneActivity
import com.example.practice.utils.LoadingDialogUtils


class ChargeActivity : BaseActivity<ActivityChargeBinding>(ActivityChargeBinding::inflate) {
    private lateinit var chargeViewModel : ChargeViewModel
    private val requestBody = ChargeDataRequestBean("123456",10000)
    private var mLoadingDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initView()
    }

    private fun initData() {
        chargeViewModel = ViewModelProvider(this)[ChargeViewModel::class.java]
    }

    private fun initView(){
        val titleView: TextView = viewBinding.titleCharge.title
        val backIBtn:ImageView = viewBinding.titleCharge.titleBtn
        val inputChargeEdt: EditText = viewBinding.chargeInput
        val chargeBtn: Button = viewBinding.payBtn
        var loadingDialog = LoadingDialogUtils()
        backIBtn.visibility = View.VISIBLE
        backIBtn.setImageResource(R.mipmap.white_back)
        titleView.text = resources.getString(R.string.recharge)
        inputChargeEdt.setRawInputType(EditorInfo.TYPE_CLASS_NUMBER)
        backIBtn.setOnClickListener {
            finish()
        }
        chargeBtn.setOnClickListener {
            chargeViewModel.requestChargeData(requestBody)
        }
        chargeViewModel.loadingLiveData.observe(this,{
            if(it){
                mLoadingDialog = loadingDialog.createLoadingDialog(this,"Loading")
            } else {
                loadingDialog.closeDialog(mLoadingDialog)
                val bundle = Bundle()
                bundle.putInt("charge_value",chargeViewModel.chargeData.value?.getOrNull()?.chargeData!!.charge)
                val intent = Intent(this, ChargeDoneActivity().javaClass)
                intent.putExtras(bundle)
                startActivity(intent)
                finish()
            }
        })
    }
}