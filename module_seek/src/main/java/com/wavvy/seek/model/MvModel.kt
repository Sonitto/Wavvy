package com.wavvy.seek.model

import com.wavvy.net.RetrofitClient
import com.wavvy.seek.data.MvData
import com.wavvy.seek.data.MvUrlData
import com.wavvy.seek.net.SeekNetService

/*
 * @Module     : com.wavvy.seek.model
 * @FileName   : MvModel
 * @Author     : lsj
 * @CreateTime : 2026/7/21 18:14
 * @Desc       : 
 */
class MvModel {
    private val api= RetrofitClient.create(SeekNetService::class.java)
    suspend fun fetchMvURLData(id: Long): Result<MvUrlData> =try{
        val response= api.getMvUrl(id)
        Result.success(response)
    }catch (e: Exception){
        Result.failure(e)
    }

}