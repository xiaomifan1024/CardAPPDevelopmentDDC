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
 * サーバーのURL：https://fxvnv345tf.execute-api.ap-northeast-1.amazonaws.com/
 */
object NetworkApi : BaseNetworkApi<INetworkService>("https://fxvnv345tf.execute-api.ap-northeast-1.amazonaws.com/") {

    suspend fun requestHistoryInfo() = getResult {
        service.requestHistoryInfo()
    }
}