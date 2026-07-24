package com.wavvy.seek.data

import com.google.gson.annotations.SerializedName

/*
 * @Module     : com.wavvy.seek.data
 * @FileName   : MvUrlData
 * @Author     : lsj
 * @CreateTime : 2026/7/21 15:23
 * @Desc       : 
 */
data class MvUrlData (
    @SerializedName("code") val code: Int,
    @SerializedName("data") val data: Data
)
data class Data(
    @SerializedName("url") val url: String
)