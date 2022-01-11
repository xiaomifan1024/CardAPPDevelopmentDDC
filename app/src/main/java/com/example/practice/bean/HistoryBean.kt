package com.example.practice.bean

data class HistoryBean(
    val dataList: List<Data>,
    val dateEnd: String,
    val dateStart: String,
    val name: String
)

data class Data(
    val address: String,
    val date: String,
    val price: String
)