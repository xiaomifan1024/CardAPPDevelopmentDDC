package com.example.practice.network



import com.example.practice.bean.HomeInfoBean
import com.example.practice.network.base.BaseResponse
import retrofit2.http.GET

interface INetworkService {

    @GET("api?version=v9&appid=57387343&appsecret=Ipz4hXwy")
    suspend fun requestHomeInfo(): BaseResponse<HomeInfoBean>
}