package com.example.module.login.api

import com.example.module.login.data.Guest
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * description :
 * author : Cherry77551
 * date : 2026/7/17 17:35
 */
interface GuestApi {
    @GET("register/anonimous")
    suspend fun getCookie(): Guest
}