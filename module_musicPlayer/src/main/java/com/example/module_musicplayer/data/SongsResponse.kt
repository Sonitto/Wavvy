package com.example.module_musicplayer.data

/**
 * description :一堆歌曲的响应
 * author : Cherry77551
 * date : 2026/7/20 20:30
 */
data class SongsResponse(
    val code:Int,
    val songs: List<Song>?
)
