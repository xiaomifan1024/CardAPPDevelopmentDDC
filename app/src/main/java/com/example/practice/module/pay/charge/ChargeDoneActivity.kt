package com.example.practice.module.pay.charge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.practice.R
import com.example.practice.base.BaseActivity
import com.example.practice.databinding.ActivityChargeBinding
import com.example.practice.databinding.ActivityChargeDoneBinding

class ChargeDoneActivity : BaseActivity<ActivityChargeDoneBinding>(ActivityChargeDoneBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView(){
        val backIBtn: ImageView = viewBinding.titleCharge.titleBtn
        val chargeMoney: TextView = viewBinding.money
        //タイトルの戻るボタンを設定
        backIBtn.visibility = View.VISIBLE
        backIBtn.setImageResource(R.mipmap.white_back)
        backIBtn.setOnClickListener {
            finish()
        }
        val bundle = intent.extras
        chargeMoney.text = bundle?.get("charge_value").toString()+"円"
    }
}