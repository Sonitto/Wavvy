package com.example.musicPlayer.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * description :歌手
 * author : Cherry77551
 * date : 2026/7/20 16:47
 */
@Parcelize
data class Artist(
    val id: Long,
    val name: String
): Parcelable
