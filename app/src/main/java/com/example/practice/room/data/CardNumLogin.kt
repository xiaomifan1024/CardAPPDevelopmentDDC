package com.example.practice.room.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_login_table")
data class CardNumLogin(
    @PrimaryKey
    val cardNum:String,
    @ColumnInfo(name = "date")
    val date:String,
    @ColumnInfo(name = "card_name")
    val cardName:String,
    @ColumnInfo(name = "security_code")
    val securityCode:String
)
