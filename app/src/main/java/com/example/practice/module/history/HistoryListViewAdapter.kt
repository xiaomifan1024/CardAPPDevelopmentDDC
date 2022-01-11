package com.example.practice.module.history


import android.view.View
import com.example.practice.base.list.BaseRecycleViewAdapter
import com.example.practice.bean.Data

class HistoryListViewAdapter(layoutResourceId: Int, items: List<Data>, init: (View, Data) -> Unit) :
    BaseRecycleViewAdapter<Data>(layoutResourceId, items, init)