package com.example.practice.module.pay.payment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.practice.R
import com.example.practice.base.BaseActivity
import com.example.practice.bean.PayResponseBean
import com.example.practice.databinding.ActivityPayCompletedBinding
import com.example.practice.databinding.ActivityPaymentBinding
import com.example.practice.module.MainActivity

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
        val titleText = viewBinding.titlePay.title
        val bundle = this.intent.extras
        val payInfo = bundle?.getSerializable("data") as Result<PayResponseBean>

        idFrom.text = payInfo.getOrNull()?.payInfo?.id_from.toString()
        itTo.text = payInfo.getOrNull()?.payInfo?.id_to.toString()
        money.text = payInfo.getOrNull()?.payInfo?.money.toString()+"円"
        time.text = payInfo.getOrNull()?.payInfo?.date+" "+payInfo.getOrNull()?.payInfo?.time
        hash.text = payInfo.getOrNull()?.payInfo?.hash
//        success.text = payInfo.getOrNull()?.payInfo?.success.toString()
        titleText.text = "支払い成功"
        titleBar.visibility = View.VISIBLE
        titleBar.setImageResource(R.mipmap.white_back)
        titleBar.setOnClickListener {
            val intent = Intent(this, MainActivity().javaClass)
            val bundle1 = Bundle()
            bundle1.putString("pay",payInfo.getOrNull()?.payInfo?.money.toString())
            intent.putExtras(bundle1)
            startActivity(intent)
            finish()
        }
    }
}