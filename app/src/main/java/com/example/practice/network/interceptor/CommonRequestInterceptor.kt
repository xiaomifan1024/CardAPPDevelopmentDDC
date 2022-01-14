package com.example.practice.network.interceptor

import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response

class CommonRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        // 請求へーだー
        builder.addHeader("x-api-key", "PMAK-61e1337771ce29511bf15ccc-5bda86e21e76951bfc72dd27e83c3aa1c7")
        return chain.proceed(builder.build())
    }
}