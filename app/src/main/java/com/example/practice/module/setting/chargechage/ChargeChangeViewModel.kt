package com.example.practice.module.setting.chargechage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.bean.ChangeLimitResponseBean
import com.example.practice.bean.CurrentLimitRequestBean
import com.example.practice.bean.CurrentLimitResponseBean
import com.example.practice.network.NetworkApiTest
import kotlinx.coroutines.launch

class ChargeChangeViewModel: ViewModel()  {
    val limitLiveData = MutableLiveData<Result<CurrentLimitResponseBean>>()
    val changeLimitLiveData = MutableLiveData<Result<ChangeLimitResponseBean>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun getChargeLimitData(){
        val chargeLimitData = NetworkApiTest("https://69761cfd-52c2-4d60-88bd-ad55ecc7265f.mock.pstmn.io")
        viewModelScope.launch {
            val requestData = chargeLimitData.requestGetCurrentLimit()
            limitLiveData.value = requestData
        }
    }

    fun chargeLimitDataRequest(request: CurrentLimitRequestBean){
        val changeData = NetworkApiTest("https://e5ffd0be-4d25-43eb-9e85-41ffcd0f62e6.mock.pstmn.io")
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            val requestData = changeData.requestCurrentLimit(request)
            changeLimitLiveData.value = requestData
            loadingLiveData.postValue(false)
        }
    }
}