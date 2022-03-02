package com.example.practice.bean

data class AccountDelResponseBean(
    val accountDelInfo: AccountDelInfo
)
data class AccountDelInfo(
    val isSuccess:Boolean,
    val msg:String)