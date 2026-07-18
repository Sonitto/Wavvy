package com.wavvy.seek.data

import com.google.gson.annotations.SerializedName

/*
 * @Module     : com.wavvy.seek.data
 * @FileName   : SingerData
 * @Author     : lsj
 * @CreateTime : 2026/7/17 21:05
 * @Desc       : 歌手列表
 */
data class SeekSingerData (
    @SerializedName("code") val code: Int,
    @SerializedName("result") val result: SingerResult
)
data class SingerResult(
    @SerializedName("artists") val artists: List<ArtistSinger>,
)

data class ArtistSinger(
    @SerializedName("id") val id: Long,
    @SerializedName("mvSize") val mvSize: Int,
    @SerializedName("name") val name: String,
    @SerializedName("picUrl") val picUrl: String,
)