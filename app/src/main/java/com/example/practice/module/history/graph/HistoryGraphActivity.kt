package com.example.practice.module.history.graph

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practice.R
import com.example.practice.base.BaseActivity
import com.example.practice.bean.CircleDataBean
import com.example.practice.databinding.ActivityHistoryGraphBinding





class HistoryGraphActivity : BaseActivity<ActivityHistoryGraphBinding>(ActivityHistoryGraphBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val items = mutableListOf<CircleDataBean>()
        items.add(CircleDataBean(1, "百度",  getColor(R.color.blue)))
        items.add(CircleDataBean(23, "Google", getColor(R.color.black)))
        items.add(CircleDataBean(33, "沃尔玛",  getColor(R.color.gray)))
        items.add(CircleDataBean(10, "阿里巴巴",  getColor(R.color.purple_200)))
        items.add(CircleDataBean(12, "华为",  getColor(R.color.teal_200)))
        items.add(CircleDataBean(19, "斗鱼",  getColor(R.color.teal_700)))
        viewBinding.graphView.setItems(items)
    }
}