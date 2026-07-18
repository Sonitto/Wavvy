package com.wavvy.seek

import com.wavvy.net.RetrofitClient
import retrofit2.Retrofit

/*
 * @Module     : com.wavvy.seek
 * @FileName   : SeekResultData
 * @Author     : lsj
 * @CreateTime : 2026/7/18 12:54
 * @Desc       : 
 */
class SeekResultModel {
    private val api= RetrofitClient.create(SeekNetService::class.java)

}