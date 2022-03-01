package com.example.practice.module.setting.notification

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.bean.MessageModifyResponse
import com.example.practice.bean.NotificationEditRequestBean
import com.example.practice.bean.NotificationEditResponseBean
import com.example.practice.network.NetworkApiTest
import kotlinx.coroutines.launch

class NotificationModifyViewModel :ViewModel(){
    val messageLiveData = MutableLiveData<Result<MessageModifyResponse>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val editLiveData = MutableLiveData<Result<NotificationEditResponseBean>>()

    fun getMessageEditData() {
        val messageData = NetworkApiTest("https://91bdbbe3-c8f4-4069-805d-148e4bf7691d.mock.pstmn.io")
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            val requestData = messageData.requestGetMessageData()
            messageLiveData.value = requestData
            loadingLiveData.postValue(false)
        }

    }

    fun messageEditRequest(request: NotificationEditRequestBean) {
        val messageEditData = NetworkApiTest("https://b9e3c5fa-06e6-4638-a919-cf91dd12bbc2.mock.pstmn.io")
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            val requestData =messageEditData.requestMessageEditData(request)
            editLiveData.value = requestData
            loadingLiveData.postValue(false)
        }
    }

}