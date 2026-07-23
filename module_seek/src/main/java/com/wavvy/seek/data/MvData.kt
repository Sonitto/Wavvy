package com.wavvy.seek.data

import com.google.gson.annotations.SerializedName

/*
 * @Module     : com.wavvy.seek.data
 * @FileName   : MvData
 * @Author     : lsj
 * @CreateTime : 2026/7/19 20:17
 * @Desc       : 
 */
data class MvData (
    @SerializedName("code") val code: Int,
    @SerializedName("result") val result: List<Mv>
)
data class Mv(
    @SerializedName("artists") val artists: List<ArtistSinger>,
    @SerializedName("cover") val cover: String,//封面
    @SerializedName("duration") val duration: Long,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("playCount") val playCount:Int
)



