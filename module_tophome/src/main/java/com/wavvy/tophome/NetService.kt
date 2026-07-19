package com.wavvy.tophome

import com.wavvy.tophome.data.BannerData
import com.wavvy.tophome.data.DetailData
import com.wavvy.tophome.data.LoveSongData
import com.wavvy.tophome.data.SongSeaData
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
    @GET("toplist/detail")
    suspend fun getDetails() : DetailData
    @GET("banner")
    suspend fun getBannerList(@Query("type") type: Int = 1): BannerData
    @GET("recommend/songs")
    suspend fun getRecommendSongs() : SongSeaData
    @GET("personalized")
    suspend fun getLoveSong(): LoveSongData
}
