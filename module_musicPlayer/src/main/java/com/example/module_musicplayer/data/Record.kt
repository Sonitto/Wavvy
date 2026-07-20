package com.example.module_musicplayer.data

/**
 * description :播放历史
 * author : Cherry77551
 * date : 2026/7/20 19:42
 */
data class RecordResponse(
    val code: Int,
    val allData: List<Record>?
)

data class Record(
    val playCount: Int,
    val song: Song
)
