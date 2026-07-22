package com.example.module_find

import com.example.module_find.data.RankData
import com.wavvy.net.RetrofitClient

/*
 * @Module     : com.example.module_find.data
 * @FileName   : FindModel
 * @Author     : lsj
 * @CreateTime : 2026/7/20 22:44
 * @Desc       :
 */
class FindModel {
    private val api by lazy {
        RetrofitClient.create(FindNetSerive::class.java)
    }
    suspend fun fetchRankData(): Result<RankData> =try {
        val response=api.getRankData()
        Result.success(response)
    }catch (e: Exception){
        Result.failure(e)
    }
}