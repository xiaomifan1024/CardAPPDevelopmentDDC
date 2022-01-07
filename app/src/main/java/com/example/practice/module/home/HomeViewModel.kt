package com.example.practice.module.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.bean.HomeInfoBean
import com.example.practice.network.NetworkApi
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val homeInfoLiveData = MutableLiveData<Result<HomeInfoBean>>()
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun requestHomeInfo() {
        viewModelScope.launch {
            val result = NetworkApi.requestHomeInfo()
            homeInfoLiveData.value = result
        }
    }
}