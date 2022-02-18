package com.example.practice.bean

data class CardLoginResponseBean(
    val msgData: MsgData
)
data class MsgData(
    val isSuccess: Boolean,
    val msg: String
)