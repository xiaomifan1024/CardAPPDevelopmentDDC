package com.example.practice.bean

data class HistoryBean(
    val dataList: List<Data>
)

data class Data(
    val address: String,
    val date: String,
    val price: String
)