package com.example.module_find

import com.example.module_find.data.RankData
import retrofit2.http.GET

/*
 * @Module     : com.example.module_find
 * @FileName   : FindNetSerive
 * @Author     : lsj
 * @CreateTime : 2026/7/20 21:51
 * @Desc       : 
 */
interface FindNetSerive {
    @GET("toplist/detail")
    suspend fun getRankData(): RankData
}