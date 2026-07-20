package com.wavvy.tophome.model

import com.wavvy.net.RetrofitClient
import com.wavvy.tophome.data.BannerData
import com.wavvy.tophome.data.DetailData
import com.wavvy.tophome.data.LoveSongData
import com.wavvy.tophome.NetService
import com.wavvy.tophome.data.SongSeaData

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
    //轮播图
    suspend fun fetchBannerData(): Result<BannerData> =try {
        val response=api.getBannerList()
        Result.success(response)
    }catch (e: Exception){
        Result.failure(e)
    }
    //歌曲推荐
    suspend fun fetchRecommendSongs(): Result<SongSeaData> =try {
        val response=api.getRecommendSongs()
        Result.success(response)
    }catch (e: Exception){
        Result.failure(e)
    }
    //网友都爱听
    suspend fun fetchLoveSong(): Result<LoveSongData> =try {
        val response=api.getLoveSong()
        Result.success(response)
    }catch (e: Exception){
        Result.failure(e)
    }
}