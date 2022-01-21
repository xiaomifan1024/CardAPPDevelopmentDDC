package com.example.practice.module.pay.charge

import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import com.example.practice.R
import com.example.practice.base.BaseActivity
import com.example.practice.databinding.ActivityChargeBinding
import android.icu.lang.UCharacter.GraphemeClusterBreak.T

import android.view.KeyEvent
import android.widget.TextView.OnEditorActionListener


class ChargeActivity : BaseActivity<ActivityChargeBinding>(ActivityChargeBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView(){
        val titleView: TextView = viewBinding.titleCharge.title
        val inputChargeEdt: EditText = viewBinding.chargeInput
        titleView.text = resources.getString(R.string.recharge)
        titleView.setTextColor(resources.getColor(R.color.black,null))
        inputChargeEdt.setRawInputType(EditorInfo.TYPE_CLASS_NUMBER)
        inputChargeEdt.setOnEditorActionListener(OnEditorActionListener { textView, actionId, keyEvent ->
            if ( actionId == EditorInfo.IME_ACTION_DONE) {
                finish()
                return@OnEditorActionListener true
            }
            false
        })

    }
}