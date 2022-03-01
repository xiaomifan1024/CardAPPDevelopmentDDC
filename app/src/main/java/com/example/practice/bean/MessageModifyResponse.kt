package com.example.practice.bean

data class MessageModifyResponse(
    val messageData:MessageData
)
data class MessageData(
    val message_title: String,
    val message_body: String
)