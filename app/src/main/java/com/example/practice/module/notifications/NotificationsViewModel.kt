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
            val notificationsData= NetworkApiTest("https://b189a1a1-137d-468c-ad1e-ecadfcd7391e.mock.pstmn.io")
            val requestValue=notificationsData.requestNotificationInfo()
            notificationsListLiveData.value=requestValue
        }
    }
}