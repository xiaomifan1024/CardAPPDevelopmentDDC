package com.example.practice.network

import android.content.Context
import com.example.practice.network.base.BaseNetworkApi
import android.util.Log
import com.example.practice.bean.HistoryBean
import com.google.gson.Gson

import java.io.IOException

import java.io.ByteArrayOutputStream

import java.io.InputStream





/**
 * ネットワーク請求実施
 * サーバーのURL：http://localhost:8080/
 */
object NetworkApi : BaseNetworkApi<INetworkService>("https://v0.yiketianqi.com/") {

    suspend fun requestHomeInfo() = getResult {
        service.requestHomeInfo()
    }
}