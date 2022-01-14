package com.example.practice.json

import com.example.practice.bean.HistoryBean

data class HistoryDataGet(
    var code: Int = 0,
    val msg: String? = null,
    val data: HistoryBean? = null)
