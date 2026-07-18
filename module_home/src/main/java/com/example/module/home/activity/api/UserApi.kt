package com.example.module.home.activity.api

import com.example.module.home.activity.data.UserAccount
import com.example.module.home.activity.data.UserDetail
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * description :
 * author : Cherry77551
 * date : 2026/7/17 22:53
 */
interface UserApi {
    @GET("user/account")
    suspend fun getAccount(): UserAccount

    @GET("user/detail")
    suspend fun getDetail(
        @Query("uid") uid: Long
    ): UserDetail
}