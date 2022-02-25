package com.example.practice.bean

data class UserInfoResponseBean(
    val userInfoData:UserInfoData
)
data class UserInfoData (
    val isSuccess: Boolean,
    val msg: String
)