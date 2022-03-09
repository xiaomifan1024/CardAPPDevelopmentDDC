package com.example.practice.module.setting.userinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.bean.CustomerInfoResponse
import com.example.practice.bean.UserInfoRequest
import com.example.practice.bean.UserInfoResponseBean
import com.example.practice.network.NetworkApiTest
import kotlinx.coroutines.launch


class UserInfoChangeViewModel: ViewModel() {
    val userInfoLiveData = MutableLiveData<Result<CustomerInfoResponse>>()
    val userChangeLiveData = MutableLiveData<Result<UserInfoResponseBean>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    var isSpinnerShow = MutableLiveData<Boolean>()

    fun getUserInfoData() {
        loadingLiveData.postValue(true)
        isSpinnerShow.postValue(true)
        val userInfoData = NetworkApiTest("https://87937c60-43bb-49e4-a700-6a09e3759310.mock.pstmn.io")
        viewModelScope.launch {
            val requestData = userInfoData.requestGetCustomerInfo()
            userInfoLiveData.value = requestData
            loadingLiveData.postValue(false)
            isSpinnerShow.postValue(false)
        }

    }

    fun requestUserInfoChange(request: UserInfoRequest){
        loadingLiveData.postValue(true)
        val userInfoChangeData = NetworkApiTest("https://8385db24-8712-46fb-b57a-557de5e54f6d.mock.pstmn.io")
        viewModelScope.launch {
            val responseBean = userInfoChangeData.requestUserInfoChange(request)
            userChangeLiveData.value = responseBean
            loadingLiveData.postValue(false)
        }
    }
}