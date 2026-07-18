package com.example.module.login.api

import com.example.module.login.data.QrCheck
import com.example.module.login.data.QrCreateResponse
import com.example.module.login.data.QrKeyResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * description :
 * author : Cherry77551
 * date : 2026/7/16 20:03
 */
interface QrApi {
    //生成key
    @GET("login/qr/key")
    suspend fun getQrKey(
        @Query("timestamp") timestamp:Long= System.currentTimeMillis()
    ):QrKeyResponse
    //生成二维码
    @GET("login/qr/create")
    suspend fun createQr(
        @Query("key") key: String,
        @Query("qrimg") qrimg: String="true",
        @Query("timestamp") timestamp:Long= System.currentTimeMillis()
    ): QrCreateResponse
    //检查扫码状态
    @GET("login/qr/check")
    suspend fun checkQr(
        @Query("key") key: String,
        @Query("timestamp") timestamp:Long= System.currentTimeMillis()
    ): QrCheck
}