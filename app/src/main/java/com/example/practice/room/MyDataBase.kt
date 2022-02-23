package com.example.practice.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practice.room.dao.CardLoginDao
import com.example.practice.room.data.CardNumLogin

@Database(entities = [CardNumLogin::class], version = 1)
abstract class MyDataBase : RoomDatabase() {
    abstract fun cardLoginDao(): CardLoginDao
}