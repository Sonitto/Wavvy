package com.example.module_musicplayer.data

/**
 * description :用户歌单
 * author : Cherry77551
 * date : 2026/7/20 17:37
 */
data class PlaylistResponse(
    val code: Int,
    val playlist: List<Playlist>?
)

data class Playlist(
    val id: Long,
    val name: String,
    val coverImgUrl: String?,
    val trackCount: Int,
    val specialType: Int //5like
)
