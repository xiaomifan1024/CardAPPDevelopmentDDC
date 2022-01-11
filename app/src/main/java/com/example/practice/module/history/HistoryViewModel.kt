package com.example.practice.module.history

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.bean.HistoryBean
import com.example.practice.json.ReadJsonFile
import kotlinx.coroutines.launch

class HistoryViewModel : ViewModel() {
    private val jsonFileName = "getHistoryData.json"
    val historyListLiveData = MutableLiveData<HistoryBean>()
    private var readJsonFile= ReadJsonFile(jsonFileName)
    private val _text = MutableLiveData<String>().apply {
        value = "This is History Fragment"
    }
    val text: LiveData<String> = _text

    fun getHistoryList(context: Context) {
        viewModelScope.launch {
            val result = readJsonFile.getHistoryListData(context,HistoryBean::class.java)
            historyListLiveData.value=result!!
        }
    }
}