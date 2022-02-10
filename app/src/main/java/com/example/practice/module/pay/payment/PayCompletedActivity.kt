package com.example.practice.module.pay.payment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.practice.base.BaseActivity
import com.example.practice.bean.PayResponseBean
import com.example.practice.databinding.ActivityPayCompletedBinding
import com.example.practice.databinding.ActivityPaymentBinding

class PayCompletedActivity : BaseActivity<ActivityPayCompletedBinding>(ActivityPayCompletedBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView(){
        val idFrom = viewBinding.idFrom
        val itTo = viewBinding.idTo
        val money = viewBinding.money
        val time = viewBinding.time
        val hash = viewBinding.hash
//        val success = viewBinding.success
        val titleBar = viewBinding.titlePay.titleBtn

        val bundle = this.intent.extras
        val payInfo = bundle?.getSerializable("data") as Result<PayResponseBean>
        idFrom.text = payInfo.getOrNull()?.payInfo?.id_from.toString()
        itTo.text = payInfo.getOrNull()?.payInfo?.id_to.toString()
        money.text = payInfo.getOrNull()?.payInfo?.money.toString()+"円"
        time.text = payInfo.getOrNull()?.payInfo?.date+" "+payInfo.getOrNull()?.payInfo?.time
        hash.text = payInfo.getOrNull()?.payInfo?.hash
//        success.text = payInfo.getOrNull()?.payInfo?.success.toString()
        titleBar.visibility = View.VISIBLE
        titleBar.setOnClickListener {
            finish()
        }
    }
}