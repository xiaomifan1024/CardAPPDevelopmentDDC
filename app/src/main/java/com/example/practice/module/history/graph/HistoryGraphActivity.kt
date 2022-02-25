package com.example.practice.module.history.graph

import android.os.Bundle
import android.widget.ImageView
import com.example.practice.R
import com.example.practice.base.BaseActivity
import com.example.practice.bean.CircleDataBean
import com.example.practice.bean.Data
import com.example.practice.bean.HistoryBean
import com.example.practice.databinding.ActivityHistoryGraphBinding
import com.example.practice.utils.CirqueStatisticalView
import android.content.Intent
import android.widget.TextView


class HistoryGraphActivity : BaseActivity<ActivityHistoryGraphBinding>(ActivityHistoryGraphBinding::inflate) {
    private val circleData = mutableListOf<CircleDataBean>()
    private var arrayList = mutableListOf<Data>()
    private var data:Data ?= null
    private var startDate = "2021年12月1日"
    private var endDate = "2021年12月31日"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        initData()
        dummyData()
        initView()
    }

    private fun initView(){
        val graphTitleImg: ImageView = viewBinding.graphImg.titleHistoryShowGraph
        val endTv: TextView = viewBinding.graphImg.dateEnd
        val startTv: TextView = viewBinding.graphImg.dateStart
        graphTitleImg.setImageResource(R.mipmap.list)
        graphTitleImg.setOnClickListener {
            finish()
        }
        val graphView : CirqueStatisticalView = viewBinding.graphView
        graphView.setItems(circleData)
        startTv.text = startDate
        endTv.text = endDate
    }


    private fun dummyData(){
        val bundle = this.intent.extras
        var i =1
        arrayList = bundle?.getSerializable("historyData") as MutableList<Data>

        var map = mutableMapOf<String, Int>()
        for (item in arrayList){
            if(map[item.address] == null){
                i =1
                map[item.address] =  i
            }else{
                var currentPrice = map[item.address]
                map[item.address] =  i + currentPrice!!
            }
        }

        var count = 0
        for (item in map){
            if(count == 0){
                circleData.add(CircleDataBean(item.value*100/arrayList.size, item.key,  getColor(R.color.light_pink)))
            }
            if(count == 1){
                circleData.add(CircleDataBean(item.value*100/arrayList.size, item.key,  getColor(R.color.light_yellow)))
            }
            if(count == 2){
                circleData.add(CircleDataBean(item.value*100/arrayList.size, item.key,  getColor(R.color.blue)))
            }
            if(count == 3){
                circleData.add(CircleDataBean(item.value*100/arrayList.size, item.key,  getColor(R.color.gray)))
            }
            count++
        }
    }





}