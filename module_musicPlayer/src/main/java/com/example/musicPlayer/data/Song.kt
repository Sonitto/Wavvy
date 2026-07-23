package com.example.musicPlayer.data

import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import kotlinx.parcelize.Parcelize

/**
 * description :歌曲类
 * author : Cherry77551
 * date : 2026/7/20 16:46
 */
@Parcelize
data class Song(
    val id: Long,
    val name: String,
    val ar: List<Artist>?,
    val dt: Long,
    val al: Album?
) : Parcelable{
    // 把歌手名拼接成"歌手A/歌手B"
    fun artistNames(): String = ar?.joinToString(" / ") { it.name } ?: "未知歌手"

    // 专辑封面路径http转https
    fun coverUrl(): String = al?.picUrl?.replace("http://", "https://") ?: ""

    // 时长格式化成 "分:秒"
    fun duration(): String {
        val sec = (dt / 1000).toInt()
        val min = sec / 60
        val s = sec % 60
        return "$min:${String.format("%02d", s)}"
    }
}
