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





class HistoryGraphActivity : BaseActivity<ActivityHistoryGraphBinding>(ActivityHistoryGraphBinding::inflate) {
    private val circleData = mutableListOf<CircleDataBean>()
    private var arrayList = mutableListOf<Data>()
    private var data:Data ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        initData()
        dummyData()
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

    private fun dummyData(){
        val bundle = this.intent.extras
        var price =1
        arrayList = bundle?.getSerializable("historyData") as MutableList<Data>
        var map = mutableMapOf<String, Int>()
        for (item in arrayList){
            if(map[item.address] == null){
                price =1
                map[item.address] = price
            }else{
                var currentPrice = map[item.address]
                map[item.address] = price + currentPrice!!
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