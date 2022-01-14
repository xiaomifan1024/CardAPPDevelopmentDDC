package com.example.practice.module.history

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.bean.HistoryBean
import com.example.practice.network.NetworkApi
import kotlinx.coroutines.launch

class HistoryViewModel : ViewModel() {
//    private val jsonFileName = "getHistoryData.json"
    val historyListLiveData = MutableLiveData<Result<HistoryBean>>()
//    val historyFromJsonFile= MutableLiveData<HistoryDataGet>()// read data from jsonfile
//    private var readJsonFile= ReadJsonFile(jsonFileName)// read data from jsonfile
    private val _text = MutableLiveData<String>().apply {
        value = "This is History Fragment"
    }
    val text: LiveData<String> = _text

    fun getHistoryList(context: Context) {
        viewModelScope.launch {
            // read data from jsonfile
//            val resultFromFile = readJsonFile.getHistoryListData(context, HistoryDataGet::class.java)
//            historyFromJsonFile.value=resultFromFile!!// read data from jsonfile
            // read data from networkapi
            val resultFromNetwork = NetworkApi.requestHistoryInfo()
            historyListLiveData.value=resultFromNetwork
        }
    }
}