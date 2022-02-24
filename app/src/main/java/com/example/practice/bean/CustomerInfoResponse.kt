package com.example.practice.bean

data class CustomerInfoResponse(
    val customerData:CustomerData
)
data class CustomerData(
    val eZipCode: String,
    val etBirthday: String,
    val etEmail: String,
    val etName1: String,
    val etName2: String,
    val etPassword1: String
)
