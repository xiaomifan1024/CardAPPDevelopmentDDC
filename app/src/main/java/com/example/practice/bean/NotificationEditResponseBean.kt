package com.example.practice.bean

data class NotificationEditResponseBean(
    val messageData: MessageEditData
)
data class MessageEditData(
    val isSuccess: Boolean,
    val msg: String
)