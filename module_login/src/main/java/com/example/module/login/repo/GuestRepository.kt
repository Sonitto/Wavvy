package com.example.module.login.repo

import com.example.module.login.api.GuestApi
import com.example.module.login.api.QrApi
import com.example.module.login.data.Guest
import com.wavvy.net.RetrofitClient

/**
 * description :
 * author : Cherry77551
 * date : 2026/7/17 17:42
 */
class GuestRepository {

    private val guestApi: GuestApi by lazy{
        RetrofitClient.create(GuestApi::class.java)}
    suspend fun guestLogin(): Guest{
        return guestApi.getCookie()
    }
}