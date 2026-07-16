package com.wavvy.tophome;
import retrofit2.http.GET;
/*
 * @Module     : com.wavvy.tophome
 * @FileName   : NetService
 * @Author     : lsj
 * @CreateTime : 2026/7/16 10:45
 * @Desc       :
 */
interface NetService {
    @GET("playlist/hot")
}
