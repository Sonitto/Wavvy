package com.wavvy.tophome.data

import com.google.gson.annotations.SerializedName

/*
 * @Module     : com.wavvy.tophome
 * @FileName   : BannerData
 * @Author     : lsj
 * @CreateTime : 2026/7/16 15:47
 * @Desc       : 
 */
data class BannerData(
    @SerializedName("code")
    val code: Int,
    @SerializedName("banners")
    val bannerList: List<BannerList>
)
data class BannerList(
    @SerializedName("pic") //图片
    val pic: String,
    @SerializedName("targetType")
    val targetType: Int?,
    @SerializedName("typeTitle")
    val typeTitle: String?,
    @SerializedName("url")//内容
    val url: String?,
    @SerializedName("targetId")
    val targetId: Long?
)

