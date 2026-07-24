package com.wavvy.seek.net

import com.wavvy.seek.data.MusicRankData
import com.wavvy.seek.data.MvData
import com.wavvy.seek.data.MvUrlData
import com.wavvy.seek.data.SeekLyricData
import com.wavvy.seek.data.SeekSingerData
import com.wavvy.seek.data.SingleData
import com.wavvy.seek.data.SongListData
import retrofit2.http.GET
import retrofit2.http.Query

/*
 * @Module     : com.wavvy.seek
 * @FileName   : SeekNetService
 * @Author     : lsj
 * @CreateTime : 2026/7/17 20:03
 * @Desc       :
 */
interface SeekNetService {
    //搜索
    @GET("search")
    suspend fun getSingleData(
        @Query("keywords") keyword: String,
        @Query("type") type: Int
    ): SingleData

    @GET("search")
    suspend fun getSongList(
        @Query("keywords") keyword: String,
        @Query("type") type: Int
    ): SongListData

    @GET("search")
    suspend fun getLyricData(
        @Query("keywords") keyword: String,
        @Query("type") type: Int
    ): SeekLyricData
    @GET("search")
    suspend fun getSingerData(
        @Query("keywords") keyword: String,
        @Query("type") type: Int
    ): SeekSingerData
    suspend fun getMvData(
        @Query("keywords") keyword: String,
        @Query("type") type: Int
    ): MvData
//搜索面的榜单
    @GET("playlist/detail")
    suspend fun getMusicRank(
        @Query("id")id: Int
    ): MusicRankData
    @GET("mv/url")
    suspend fun getMvUrl(
        @Query("id")id:Long
    ): MvUrlData

}