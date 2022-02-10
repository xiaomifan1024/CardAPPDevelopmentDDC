package com.example.practice.network

import com.example.practice.bean.PayRequestBean
import com.example.practice.network.base.BaseNetworkApi

class NetworkApiTest(url:String) : BaseNetworkApi<INetworkService>(url){
    //残高APIをコール
    suspend fun requestLeftMoney() = getResult {
        service.requestLeftMoney()
    }
    //お知らせAPIをコール
    suspend fun requestNotificationInfo() = getResult {
        service.requestNotificationInfo()
    }
    //お支払うAPIをコール
    suspend fun requestPaymentInfo(request: PayRequestBean) = getResult {
        service.requestPaymentInfo(request)
    }
}