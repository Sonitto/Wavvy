package com.example.musicPlayer.data

/**
 * description :歌单里面的歌曲
 * author : Cherry77551
 * date : 2026/7/20 19:41
 */
data class PlaylistDetailResponse(
    val code: Int,
    val playlist: PlaylistDetail?
)

data class PlaylistDetail(
    val id: Long,
    val name: String,
    val coverImgUrl: String?,
    val tracks: List<Song>?
)
