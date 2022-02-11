package com.example.practice.module.pay

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.example.practice.base.BaseActivity
import com.example.practice.databinding.ActivityScanQrResultBinding

class ScanQrResultActivity : BaseActivity<ActivityScanQrResultBinding>(ActivityScanQrResultBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val title: TextView = viewBinding.title.title
        val titleBtn: ImageButton = viewBinding.title.titleBtn
        title.text = "スキャニング結果"
        titleBtn.setOnClickListener {

        }
    }
}