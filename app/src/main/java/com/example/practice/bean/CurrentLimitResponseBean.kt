package com.example.practice.bean

data class CurrentLimitResponseBean(
    val currentLimitData: CurrentLimitData
)
data class CurrentLimitData(
    val isSuccess: Boolean,
    val currentLimit: Int
)