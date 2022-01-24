package com.example.practice.module.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.bean.HistoryBean
import com.example.practice.bean.NotificationBean
import com.example.practice.network.NetworkApi
import com.example.practice.network.NetworkApiTest
import kotlinx.coroutines.launch

class NotificationsViewModel : ViewModel() {
    val notificationsListLiveData = MutableLiveData<Result<NotificationBean>>()
    fun getNotificationsList() {
        viewModelScope.launch {
            val notificationsData= NetworkApiTest("https://539f2121-ba1c-4290-8c03-ff58c2061a80.mock.pstmn.io")
            val requestValue=notificationsData.requestNotificationInfo()
            notificationsListLiveData.value=requestValue
        }
    }
}