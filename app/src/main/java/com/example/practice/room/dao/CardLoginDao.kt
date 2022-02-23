package com.example.practice.room.dao

import androidx.room.*
import com.example.practice.room.data.CardNumLogin

@Dao
interface CardLoginDao {
    @Query("SELECT * FROM card_login_table")
    fun getAll(): List<CardNumLogin>

    @Query("SELECT * FROM card_login_table WHERE cardNum IN (:cardNumbers)")
    fun loadAllByNumbers(cardNumbers: List<String>): List<CardNumLogin>

    @Query("SELECT * FROM card_login_table WHERE cardNum  = :cardNum")
    fun findByNumber(cardNum: String): CardNumLogin

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cardLoginData:CardNumLogin)

    @Update
    fun update(cardLoginData: CardNumLogin)
}