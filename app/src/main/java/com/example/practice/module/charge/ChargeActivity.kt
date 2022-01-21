package com.example.practice.module.charge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.practice.R
import com.example.practice.base.BaseActivity
import com.example.practice.databinding.ActivityChargeBinding
import com.example.practice.databinding.ActivityMainBinding

class ChargeActivity : BaseActivity<ActivityChargeBinding>(ActivityChargeBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView(){
        val titleView: TextView = viewBinding.titleCharge.title
        titleView.text = resources.getString(R.string.recharge)
        titleView.setTextColor(resources.getColor(R.color.black,null))

    }
}