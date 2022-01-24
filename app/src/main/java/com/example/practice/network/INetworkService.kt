package com.example.practice.network



import com.example.practice.bean.*
import com.example.practice.network.base.BaseResponse
import retrofit2.http.GET

interface INetworkService {

    @GET("dev/homeinfo")
    suspend fun requestHomeInfo(): BaseResponse<HomeInfoBean>

    @GET("dev/history")
    suspend fun requestHistoryInfo(): BaseResponse<HistoryBean>

    @GET("dev/left_money")
    suspend fun requestLeftMoney(): BaseResponse<LeftMoneyBean>

    @GET("dev/notification_data")
    suspend fun requestNotificationInfo(): BaseResponse<NotificationBean>
}