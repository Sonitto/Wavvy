package com.wavvy.tophome

import com.wavvy.net.RetrofitClient

/*
 * @Module     : com.wavvy.tophome
 * @FileName   : TopModel
 * @Author     : lsj
 * @CreateTime : 2026/7/16 15:03
 * @Desc       : 
 */
class TopModel {
    private val api by lazy {
        RetrofitClient.create(NetService::class.java)
    }
    suspend fun fetchDetailData(): Result<DetailData> = try {
        val response=api.getDetails()
        Result.success(response)
    }catch(e:Exception){
        Result.failure(e)
    }
    suspend fun fetchBannerData(): Result<BannerData> =try {
        val response=api.getBannerList()
        Result.success(response)
    }catch (e: Exception){
        Result.failure(e)
    }
}