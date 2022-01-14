package com.example.practice.network.base

import java.util.*

/**
 * ネットワーク応答
 */
data class BaseResponse<T>(
    var code: Int = 0,
    val msg: String? = null,
    val data: T? = null
)
