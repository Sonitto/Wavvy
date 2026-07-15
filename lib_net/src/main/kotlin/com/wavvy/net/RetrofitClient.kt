package com.wavvy.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/*
 * @Module     : com.wavvy.lib
 * @FileName   : net
 * @Author     : lsj
 * @CreateTime : 2026/7/15 14:03
 * @Desc       : 
 */
object RetrofitClient{
private const val BASE_URL="11111"
private val okHttpClient = OkHttpClient.Builder()
        .callTimeout(300, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(com.wavvy.net.RetrofitClient.okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
    fun <T> create(serviceClass: Class<T>): T = com.wavvy.net.RetrofitClient.retrofit.create(serviceClass)
}