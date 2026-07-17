package com.example.module.login.repo

import com.example.module.login.api.QrApi
import com.example.module.login.data.QrCheck
import com.example.module.login.data.QrCreateResponse
import com.example.module.login.data.QrKeyResponse
import com.wavvy.net.RetrofitClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers


/**
 * description :
 * author : Cherry77551
 * date : 2026/7/16 21:22
 */
class QrRepository {
    private val api: QrApi by lazy{
        RetrofitClient.create(QrApi::class.java)}

    suspend fun getQrKey(): QrKeyResponse {
        return api.getQrKey()

    }

    suspend fun createQr(key: String):QrCreateResponse{
        return api.createQr(key)

    }

    suspend fun checkQr(key: String): QrCheck{
        return api.checkQr(key)

    }
}