package com.example.practice.module.setting.userinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.bean.CustomerInfoResponse
import com.example.practice.network.NetworkApiTest
import kotlinx.coroutines.launch


class UserInfoChangeViewModel: ViewModel() {
    val userInfoLiveData = MutableLiveData<Result<CustomerInfoResponse>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun getUserInfoData() {
        loadingLiveData.postValue(true)
        val userInfoData = NetworkApiTest("https://87937c60-43bb-49e4-a700-6a09e3759310.mock.pstmn.io")
        viewModelScope.launch {
            val requestData = userInfoData.requestGetCustomerInfo()
            userInfoLiveData.value = requestData
            loadingLiveData.postValue(false)
        }

    }
}