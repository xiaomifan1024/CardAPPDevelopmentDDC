package com.example.practice.module.setting.cardlogin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.bean.CardLoginRequestBean
import com.example.practice.bean.CardLoginResponseBean
import com.example.practice.network.NetworkApiTest
import kotlinx.coroutines.launch

class CardLoginViewModel  : ViewModel() {
    val cardLoginLiveData = MutableLiveData<Result<CardLoginResponseBean>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    var cardLoginData = cardLoginLiveData.value

    fun cardNumberLogin(request: CardLoginRequestBean) {
        val cardData = NetworkApiTest("https://139695a2-60bf-4b62-8d0a-6514558bd537.mock.pstmn.io")
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            cardLoginData = cardData.requestCardNumLogin(request)
            loadingLiveData.postValue(false)
        }
    }

}