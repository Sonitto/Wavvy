package com.wavvy.seek.model

import com.wavvy.net.RetrofitClient
import com.wavvy.seek.data.MvData
import com.wavvy.seek.data.SeekLyricData
import com.wavvy.seek.data.SeekSingerData
import com.wavvy.seek.data.SingleData
import com.wavvy.seek.data.SongListData
import com.wavvy.seek.net.SeekNetService
import okhttp3.HttpUrl
import retrofit2.http.Query

/*
 * @Module     : com.wavvy.seek
 * @FileName   : SeekResultData
 * @Author     : lsj
 * @CreateTime : 2026/7/18 12:54
 * @Desc       :
 */
class SeekResultModel {
    private val api= RetrofitClient.create(SeekNetService::class.java)

    suspend fun fetchSingleData(keyword: String): Result<SingleData> =try{
        val response= api.getSingleData(keyword,1)
        Result.success(response)
    }catch (e: Exception){
        Result.failure(e)
    }

    suspend fun fetchLyricData(keyword: String): Result<SeekLyricData> =try{
        val response= api.getLyricData(keyword,1006)
        Result.success(response)
    }catch (e: Exception){
        Result.failure(e)
    }

    suspend fun fetchSingerData(keyword: String): Result<SeekSingerData> =try{
        val response= api.getSingerData(keyword,100)
        Result.success(response)
    }catch (e: Exception){
        Result.failure(e)
    }

    suspend fun fetchMvData(keyword: String): Result<MvData> =try{
        val response= api.getMvData(keyword,1004)
        Result.success(response)
    }catch (e: Exception){
        Result.failure(e)
    }
    suspend fun fetchSongListData(keyword: String): Result<SongListData> =try{
        val response= api.getSongList(keyword,1000)
        Result.success(response)
    }catch (e: Exception){
        Result.failure(e)
    }







}