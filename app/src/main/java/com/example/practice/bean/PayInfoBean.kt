package com.example.practice.bean

data class PayInfoBean(
    val content: String,
    val isPayed: Boolean,
    val name: String,
    val payInfo: PayInfo,
    val payMsg: String
)

data class PayInfo(
    val date: String,
    val leftPrice: String,
    val price: String
)