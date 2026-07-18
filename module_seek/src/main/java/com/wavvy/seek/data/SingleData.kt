package com.wavvy.seek.data

import com.google.gson.annotations.SerializedName

/*
 * @Module     : com.wavvy.seek.data
 * @FileName   : SingleData
 * @Author     : lsj
 * @CreateTime : 2026/7/17 21:16
 * @Desc       : 单曲
 */
data class SingleData(
    @SerializedName("code") val code: Int,
    @SerializedName("result") val result: Results
)
data class Results(
    @SerializedName("songCount") val songCount: Int,
    @SerializedName("songs") val songs: List<Songs>
)
data class Songs(
    @SerializedName("album") val album: Album,
    @SerializedName("artists") val artists: List<Artist>,
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
)