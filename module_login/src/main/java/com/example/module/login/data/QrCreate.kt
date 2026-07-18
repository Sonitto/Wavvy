package com.example.module.login.data

/**
 * description :
 * author : Cherry77551
 * date : 2026/7/16 19:46
 */
data class QrCreate(
    val qrurl: String?,//二维码内容
    val qrimg: String? //二维码图片
)
data class QrCreateResponse(
    val code: Int,
    val data: QrCreate
)
