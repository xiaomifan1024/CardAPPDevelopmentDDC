package com.example.practice.module.pay.payment

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.practice.base.BaseActivity
import com.example.practice.databinding.ActivityPaymentBinding
import android.widget.TextView.OnEditorActionListener
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.practice.R
import com.example.practice.bean.PayRequestBean
import com.example.practice.module.pay.PayViewModel
import com.example.practice.utils.LoadingDialogUtils

class PaymentActivity : BaseActivity<ActivityPaymentBinding>(ActivityPaymentBinding::inflate) {
    private val paymentViewModel: PaymentViewModel by viewModels()
    private val requestData = PayRequestBean("2022/01/01","8018155fe6dca2ef3e713e6ecbc4e6b5649facd6fe12306f8f9d1c38dae0ea79",
        1234567890, 2345678901,50000,"12:00")

    private var mLoadingDialog: Dialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initView()
    }

    private fun initData(){

    }

    private fun initView() {
        val backIBtn: ImageView = viewBinding.titlePay.titleBtn
        val titleText: TextView = viewBinding.titlePay.title
        val inputEdt: EditText = viewBinding.payInput
        val payBtn: Button = viewBinding.payBtn
        var loadingDialog = LoadingDialogUtils()
        backIBtn.visibility = View.VISIBLE
        backIBtn.setImageResource(R.mipmap.white_back)
        titleText.text = "お支払い"
        backIBtn.setOnClickListener {
            finish()
        }
        inputEdt.setRawInputType(EditorInfo.TYPE_CLASS_NUMBER)
        inputEdt.setOnEditorActionListener(OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {

                return@OnEditorActionListener true
            }
            false
        })
        payBtn.setOnClickListener {
            paymentViewModel.getPaymentInfo(requestData)
        }
        paymentViewModel.loadingLiveData.observe(this,  {
            if(it){
                mLoadingDialog = loadingDialog.createLoadingDialog(this,"Loading")
            } else {
                loadingDialog.closeDialog(mLoadingDialog)
                var bundle = Bundle()
                bundle.putSerializable("data",paymentViewModel.paymentInfo)
                var intent = Intent(this, PayCompletedActivity().javaClass)
                intent.putExtras(bundle)
                startActivity(intent)
                finish()
            }
        })
    }
}