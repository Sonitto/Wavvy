package com.example.module_find.data

import com.google.gson.annotations.SerializedName

/*
 * @Module     : com.example.module_find.data
 * @FileName   : RankData
 * @Author     : lsj
 * @CreateTime : 2026/7/20 22:01
 * @Desc       : 
 */
data class RankData (
    @SerializedName("code") val code: Long,
    @SerializedName("list") val list: List<DetailRank>,
)
data class DetailRank(
    @SerializedName("coverImgUrl") val coverImgUrl: String,
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("trackCount") val trackCount: Long
)

