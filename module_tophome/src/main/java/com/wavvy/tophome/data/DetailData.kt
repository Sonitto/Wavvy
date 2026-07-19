package com.wavvy.tophome.data

import com.google.gson.annotations.SerializedName

/*
 * @Module     : com.wavvy.tophome
 * @FileName   : DetailData
 * @Author     : lsj
 * @CreateTime : 2026/7/16 14:32
 * @Desc       : 
 */
data class DetailData (
    @SerializedName("code") val code: Long,
    @SerializedName("list") val list: List<RankPlaylist>,
)
data class RankPlaylist(
    @SerializedName("id")//歌曲id
    val playlistId: Long,
    @SerializedName("name")//榜单名
    val playlistName: String,
    @SerializedName("coverImgUrl")
    val coverImgUrl: String?,
    @SerializedName("description")//封面图片
    val desc: String?,
    @SerializedName("playCount")
    val playCount: Long,
    @SerializedName("subscribedCount")
    val subscribeCount: Long,
    @SerializedName("trackCount")
    val totalSongNum: Int,
    @SerializedName("updateFrequency")
    val updateTip: String?,//更新速度
    @SerializedName("ToplistType")
    val topListType: String?,
    @SerializedName("tracks")
    val trackList: List<DetailTrack>?,//歌曲
    @SerializedName("updateTime")
    val updateTime: Long,
    @SerializedName("privacy")
    val privacy: Int,
    @SerializedName("status")
    val status: Int
)
data class DetailTrack(
    @SerializedName("first") val first: String,//歌名
    @SerializedName("second") val second: String //作者
)


