package com.example.musicPlayer.api

import com.example.musicPlayer.data.PlaylistDetailResponse
import com.example.musicPlayer.data.PlaylistResponse
import com.example.musicPlayer.data.RecordResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * description :歌单lei接口
 * author : Cherry77551
 * date : 2026/7/20 20:10
 */
interface PlaylistApi {
    //获取歌单列表
    @GET("user/playlist")
    suspend fun getPlaylist(
        @Query("uid") uid: Long
    ): PlaylistResponse
    //获取歌单里面所有歌曲
    @GET("playlist/track/all")
    suspend fun getDetail(
        @Query("id") id: Long
    ): PlaylistDetailResponse
    //获取播放历史
    @GET("user/record")
    suspend fun getRecord(
        @Query("uid") uid: Long,
        @Query("type") type: Int=0
    ): RecordResponse
}
