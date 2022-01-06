package com.example.practice.network

import com.example.practice.network.base.BaseNetworkApi


/**
 * ネットワーク請求実施
 * サーバーのURL：http://localhost:8080/
 */
object NetworkApi : BaseNetworkApi<INetworkService>("https://v0.yiketianqi.com/") {

    suspend fun requestHomeInfo() = getResult {
        service.requestHomeInfo()
    }
}