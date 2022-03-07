package com.example.practice.utils

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.widget.TextView

class DatePickerView(context: Context) {
    private val mContext = context

    fun showDatePickerDialog(tv: TextView) {
        val ca = Calendar.getInstance()
        var mYear = ca[Calendar.YEAR]
        var mMonth = ca[Calendar.MONTH]
        var mDay = ca[Calendar.DAY_OF_MONTH]

        val datePickerDialog = mContext?.let {
            DatePickerDialog(
                it,
                { _, year, month, dayOfMonth ->
                    mYear = year
                    mMonth = month
                    mDay = dayOfMonth
                    val mDate = "${year}年${month + 1}月${dayOfMonth}日"
                    tv.text = mDate
                },
                mYear, mMonth, mDay
            )
        }
        datePickerDialog.show()
    }
}