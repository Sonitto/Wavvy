package com.wavvy.tophome

import retrofit2.http.GET
import retrofit2.http.Query

/*
 * @Module     : com.wavvy.tophome
 * @FileName   : NetService
 * @Author     : lsj
 * @CreateTime : 2026/7/16 14:58
 * @Desc       : 
 */
interface NetService {
    @GET("/toplist/detail")
    suspend fun getDetails() : DetailData
    @GET("/banner")
    suspend fun getBannerList(@Query("type") type: Int = 1): BannerData

}