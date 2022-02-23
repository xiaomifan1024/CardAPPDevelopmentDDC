package com.example.practice.module.setting.cardlogin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.bean.CardLoginRequestBean
import com.example.practice.bean.CardLoginResponseBean
import com.example.practice.network.NetworkApiTest
import com.example.practice.room.MyDataBase
import com.example.practice.room.data.CardNumLogin
import kotlinx.coroutines.launch

class CardLoginViewModel(dataBase: MyDataBase): ViewModel() {
    val cardLoginLiveData = MutableLiveData<Result<CardLoginResponseBean>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    var cardLoginData = cardLoginLiveData.value
    val cardLoginDao = dataBase.cardLoginDao()

    fun cardNumberLogin(request: CardLoginRequestBean) {
        val cardData = NetworkApiTest("https://139695a2-60bf-4b62-8d0a-6514558bd537.mock.pstmn.io")
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            cardLoginData = cardData.requestCardNumLogin(request)
            loadingLiveData.postValue(false)
        }
    }

    fun getCardLastData(): CardNumLogin? {
        var cardData = cardLoginDao.getAll()
        if(cardData.isNotEmpty()) {
            return cardData[cardData.size-1]
        }
        return null
    }

    fun insertCardData(cardData:CardNumLogin){
        cardLoginDao.insert(cardData)
    }

    fun updateCardData(cardData:CardNumLogin){
        cardLoginDao.update(cardData)
    }
}