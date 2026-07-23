package com.example.musicPlayer.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * description :专辑
 * author : Cherry77551
 * date : 2026/7/20 16:49
 */
@Parcelize
data class Album(
    val id: Long,
    val name: String,
    val picUrl: String?
): Parcelable
