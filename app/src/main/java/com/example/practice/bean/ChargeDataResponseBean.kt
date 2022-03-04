package com.example.practice.bean

data class ChargeDataResponseBean(
    val chargeData: ChargeData
)
data class ChargeData(
    val isSuccess: Boolean,
    val charge: Int

)