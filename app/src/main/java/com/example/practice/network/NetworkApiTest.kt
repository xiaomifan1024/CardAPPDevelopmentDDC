package com.example.practice.network

import com.example.practice.bean.*
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
    //カード番号登録APIをコール
    suspend fun requestCardNumLogin(request: CardLoginRequestBean) = getResult {
        service.requestCardNumLogin(request)
    }
    //限度額APIをコール
    suspend fun requestGetCurrentLimit() = getResult {
        service.requestGetCurrentLimit()
    }
    //限度額変更API
    suspend fun requestCurrentLimit(request: CurrentLimitRequestBean) = getResult {
        service.requestCurrentLimit(request)
    }
    //情報取得API
    suspend fun requestGetCustomerInfo() = getResult {
        service.requestGetCustomerInfo()
    }
    //情報変更API
    suspend fun requestUserInfoChange(request: UserInfoRequest) = getResult {
        service.requestUserInfoChange(request)
    }
}