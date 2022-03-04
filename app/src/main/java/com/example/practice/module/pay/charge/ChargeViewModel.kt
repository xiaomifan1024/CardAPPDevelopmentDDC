package com.example.practice.module.pay.charge

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.bean.ChargeDataRequestBean
import com.example.practice.bean.ChargeDataResponseBean
import com.example.practice.network.NetworkApiTest
import kotlinx.coroutines.launch

class ChargeViewModel : ViewModel()  {
    val chargeData = MutableLiveData<Result<ChargeDataResponseBean>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun requestChargeData(request: ChargeDataRequestBean) {
        val responseData = NetworkApiTest("https://40e087ef-302a-4d8a-8843-2b49e0c71445.mock.pstmn.io")
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            chargeData.value = responseData.requestChargeData(request)
            loadingLiveData.postValue(false)
        }

    }



}