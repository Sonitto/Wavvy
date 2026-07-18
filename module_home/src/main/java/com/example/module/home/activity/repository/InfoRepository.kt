package com.example.module.home.activity.repository

import com.example.module.home.activity.api.UserApi
import com.wavvy.net.RetrofitClient

/**
 * description :
 * author : Cherry77551
 * date : 2026/7/17 23:10
 */
class InfoRepository {
    private val api: UserApi by lazy{
        RetrofitClient.create(UserApi::class.java)
    }

    suspend fun getAccount()=api.getAccount()
    suspend fun getDetail(uid: Long)=api.getDetail(uid)
}