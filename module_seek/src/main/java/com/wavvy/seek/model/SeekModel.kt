package com.wavvy.seek.model

import com.wavvy.net.RetrofitClient
import com.wavvy.seek.net.SeekNetService
import com.wavvy.seek.data.MusicRankData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/*
 * @Module     : com.wavvy.seek
 * @FileName   : SeekModel
 * @Author     : lsj
 * @CreateTime : 2026/7/18 10:59
 * @Desc       :
 */
class SeekModel{
    private val api= RetrofitClient.create(SeekNetService::class.java)
    fun fetchMusicRank(): Flow<MusicRankData> = flow {
        val ids = listOf(19723756, 3779629, 2884035)
        for (id in ids) {
            try {
                val data = api.getMusicRank(id)
                emit(data)
            } catch (e: Exception) {
                println("单条请求失败，继续下一条：${e.message}")
            }
        }
    }.flowOn(Dispatchers.IO)
}