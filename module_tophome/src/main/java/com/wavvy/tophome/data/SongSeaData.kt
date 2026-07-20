package com.wavvy.tophome.data

/*
 * @Module     : com.wavvy.tophome
 * @FileName   : SongSeaData
 * @Author     : lsj
 * @CreateTime : 2026/7/17 11:15
 * @Desc       : 
 */
import com.google.gson.annotations.SerializedName

/** 每日推荐顶层 */
data class SongSeaData(
    @SerializedName("code") val code: Int,
    @SerializedName("data") val data: DailyRecommendData
)

data class DailyRecommendData(
    @SerializedName("dailySongs") val dailySongs: List<DailySongList>
)

data class DailySongList(
    // 歌曲名
    @SerializedName("name") val songName: String,
    // 歌曲id
    @SerializedName("id") val songId: Long,
    // 歌手列表
    @SerializedName("ar") val artistList: List<Artist>,
    // 专辑（封面图）
    @SerializedName("al") val album: Album,
    // 标准音质播放链接信息
    @SerializedName("m") val musicUrlInfo: Quality?
) {
    // 拼接歌手名
    fun getSinger(): String = artistList.joinToString("/") { it.name }
}

// 歌手
data class Artist(
    @SerializedName("name") val name: String
)

// 专辑封面
data class Album(
    @SerializedName("picUrl") val coverImg: String?
)

// 播放链接（仅拿size/br等，实际url需要拼接接口）
data class Quality(
    @SerializedName("br") val bitRate: Int,
    @SerializedName("size") val fileSize: Long
)
