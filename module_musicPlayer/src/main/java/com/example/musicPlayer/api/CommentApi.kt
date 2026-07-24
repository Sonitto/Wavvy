package com.example.musicPlayer.api

import com.example.musicPlayer.data.CommentResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * description :
 * author : Cherry77551
 * date : 2026/7/24 16:35
 */
interface CommentApi {
    @GET("comment/music")
    suspend fun getComment(
        @Query("id") id: Long,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): CommentResponse
}