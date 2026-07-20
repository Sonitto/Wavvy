package com.example.module_musicplayer.api

import com.example.module_musicplayer.data.SongUrlResponse
import com.example.module_musicplayer.data.SongsResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * description :音乐单曲api
 * author : Cherry77551
 * date : 2026/7/20 20:07
 */
interface MusicApi {
    //获取音乐播放真实链接
    @GET("song/url/v1")
    suspend fun getUrl(
        @Query("id") id: Long,
        @Query("level") level: String ="exhigh" //极高音质
    ): SongUrlResponse
    //获取单曲详情
    @GET("song/detail")
    suspend fun getSong(
        @Query("ids") ids: String
    ): SongsResponse
}