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
    val loadingLiveData = MutableLiveData<Boolean>()
    val pullToRefreshLiveData = MutableLiveData<Boolean>()

    fun getNotificationsList() {
        loadingLiveData.postValue(true)
            viewModelScope.launch {
            val notificationsData= NetworkApiTest("https://bcc44455-3c2c-4c72-b417-470a1c5e2842.mock.pstmn.io")
            val requestValue=notificationsData.requestNotificationInfo()
            notificationsListLiveData.value=requestValue
            loadingLiveData.postValue(false)
        }
    }

    fun getPTRNotificationsList() {
        pullToRefreshLiveData.postValue(true)
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            val notificationsData= NetworkApiTest("https://bcc44455-3c2c-4c72-b417-470a1c5e2842.mock.pstmn.io")
            val requestValue=notificationsData.requestNotificationInfo()
            notificationsListLiveData.value=requestValue
            pullToRefreshLiveData.postValue(false)
            loadingLiveData.postValue(false)
        }
    }
}