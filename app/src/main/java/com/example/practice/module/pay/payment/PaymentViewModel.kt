package com.example.practice.module.pay.payment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.bean.PayRequestBean
import com.example.practice.bean.PayResponseBean
import com.example.practice.network.NetworkApiTest
import kotlinx.coroutines.launch


class PaymentViewModel : ViewModel()  {
    val paymentLiveData = MutableLiveData<Result<PayResponseBean>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    var paymentInfo = paymentLiveData.value

    fun getPaymentInfo(request: PayRequestBean) {
        val paymentData = NetworkApiTest("https://2e9b84ba-4658-4bed-9499-cd89f96964a4.mock.pstmn.io")
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            paymentInfo = paymentData.requestPaymentInfo(request)
            loadingLiveData.postValue(false)
        }
    }

}