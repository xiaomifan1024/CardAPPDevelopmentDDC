package com.example.practice.utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.example.practice.constant.DateSelectType

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

    @SuppressLint("ResourceType")
    fun showSingleDatePickerDialog(et:TextView, type:Int) {
        val ca = Calendar.getInstance()
        var mYear = ca[Calendar.YEAR]
        var mMonth = ca[Calendar.MONTH]
        var mDay = ca[Calendar.DAY_OF_MONTH]

        val datePickerDialog = mContext?.let {
            DatePickerDialog(
                it,
                2,
                { _, year, month, dayOfMonth ->
                    mYear = year
                    mMonth = month
                    mDay = dayOfMonth
                    when(type){
                        DateSelectType.YEAR -> et.setText(mYear.toString())
                        DateSelectType.MONTH ->et.setText((mMonth+1).toString())
                        DateSelectType.DAY->et.setText(mDay.toString())
                    }
                },
                mYear, mMonth, mDay
            )
        }
        datePickerDialog.show()
        when(type){
            DateSelectType.YEAR ->
            {
                ((datePickerDialog.datePicker.getChildAt(0) as ViewGroup).getChildAt(0) as ViewGroup)
                .getChildAt(1).visibility = View.GONE
                ((datePickerDialog.datePicker.getChildAt(0) as ViewGroup).getChildAt(0) as ViewGroup)
                    .getChildAt(2).visibility = View.GONE
            }
            DateSelectType.MONTH ->
            {
                ((datePickerDialog.datePicker.getChildAt(0) as ViewGroup).getChildAt(0) as ViewGroup)
                    .getChildAt(0).visibility = View.GONE
                ((datePickerDialog.datePicker.getChildAt(0) as ViewGroup).getChildAt(0) as ViewGroup)
                    .getChildAt(2).visibility = View.GONE
            }
            DateSelectType.DAY->
            {
                ((datePickerDialog.datePicker.getChildAt(0) as ViewGroup).getChildAt(0) as ViewGroup)
                    .getChildAt(0).visibility = View.GONE
                ((datePickerDialog.datePicker.getChildAt(0) as ViewGroup).getChildAt(0) as ViewGroup)
                    .getChildAt(1).visibility = View.GONE
            }
        }
    }
}