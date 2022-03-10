package com.example.practice.constant

import androidx.annotation.IntDef

@IntDef(DateSelectType.YEAR, DateSelectType.MONTH, DateSelectType.DAY)
@Retention(AnnotationRetention.SOURCE)
annotation class DateSelectType {
    companion object {
        const val YEAR = 1
        const val MONTH = 2
        const val DAY = 3
    }
}