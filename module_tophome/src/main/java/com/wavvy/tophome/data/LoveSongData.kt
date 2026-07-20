package com.wavvy.tophome.data

import com.google.gson.annotations.SerializedName

/*
 * @Module     : com.wavvy.tophome
 * @FileName   : LoveSongData
 * @Author     : lsj
 * @CreateTime : 2026/7/18 20:20
 * @Desc       : 
 */
data class LoveSongData (
    @SerializedName("result") val result: List<LoveSong>
)
data class LoveSong(
    @SerializedName("copywriter") val copywriter: String,//歌单文案
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("picUrl") val picUrl: String,
    @SerializedName("playCount") val playCount: Long,
    @SerializedName("trackCount") val trackCount: Long,//歌曲数量
    @SerializedName("trackNumberUpdateTime") val trackNumberUpdateTime: Long,
    @SerializedName("type") val type: Long//类型
)
