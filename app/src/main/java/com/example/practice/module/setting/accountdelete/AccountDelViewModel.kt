package com.example.practice.module.setting.accountdelete

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice.bean.AccountDelRequestBean
import com.example.practice.bean.AccountDelResponseBean
import com.example.practice.network.NetworkApiTest
import kotlinx.coroutines.launch

class AccountDelViewModel: ViewModel() {
    val accountDelLiveData = MutableLiveData<Result<AccountDelResponseBean>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun requestAccountDel(request: AccountDelRequestBean) {
        val requestData = NetworkApiTest("https://228f8309-4f16-4d0d-9f5e-3c3a518ab204.mock.pstmn.io")
        loadingLiveData.postValue(true)
        viewModelScope.launch {
            accountDelLiveData.value = requestData.requestAccountDataDel(request)
            loadingLiveData.postValue(false)
        }

    }
}