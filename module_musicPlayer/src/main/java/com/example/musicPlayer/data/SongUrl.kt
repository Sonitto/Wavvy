package com.example.musicPlayer.data

/**
 * description :歌曲播放路径
 * author : Cherry77551
 * date : 2026/7/20 16:51
 */
data class SongUrl(
    val id: Long,
    val url: String?
)

data class SongUrlResponse(
    val code: Int,
    val data: List<SongUrl>?
)


