package com.example.module.login.data

/**
 * description :
 * author : Cherry77551
 * date : 2026/7/16 19:37
 */
data class QrKey(
    val unikey: String
)
data class QrKeyResponse(
    val code: Int,
    val data: QrKey
)
