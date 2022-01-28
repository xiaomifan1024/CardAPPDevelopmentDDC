package com.example.practice.bean

import java.io.Serializable

data class HistoryBean(
    var dataList: List<Data>
)

data class Data (
    var address: String,
    var date: String,
    var price: String
):Serializable