package com.example.practice.module.history.graph

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.example.practice.R
import com.example.practice.base.BaseActivity
import com.example.practice.bean.CircleDataBean
import com.example.practice.databinding.ActivityHistoryGraphBinding
import com.example.practice.module.history.HistoryFragment
import com.example.practice.utils.CirqueStatisticalView


class HistoryGraphActivity : BaseActivity<ActivityHistoryGraphBinding>(ActivityHistoryGraphBinding::inflate) {
    private val circleData = mutableListOf<CircleDataBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initView()
    }

    private fun initView(){
        val graphTitleImg: ImageView = viewBinding.graphImg.titleHistoryShowGraph
        graphTitleImg.setImageResource(R.mipmap.list)
        graphTitleImg.setOnClickListener {
            finish()
        }
        val graphView : CirqueStatisticalView = viewBinding.graphView
        graphView.setItems(circleData)
    }

    private fun initData(){
        circleData.add(CircleDataBean(20, "ルミネ新宿",  getColor(R.color.light_pink)))
        circleData.add(CircleDataBean(23, "アトレ吉祥寺", getColor(R.color.light_yellow)))
        circleData.add(CircleDataBean(33, "〇〇〇",  getColor(R.color.blue)))
        circleData.add(CircleDataBean(25, "その他",  getColor(R.color.gray)))
    }
}