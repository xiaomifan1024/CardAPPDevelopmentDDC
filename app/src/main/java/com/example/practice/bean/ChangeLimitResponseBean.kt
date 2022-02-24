package com.example.practice.bean

data class ChangeLimitResponseBean(
    val changeData:ChangeData
)
data class ChangeData (
    val isSuccess: Boolean,
    val msg: String
)
