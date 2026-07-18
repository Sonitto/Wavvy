package com.wavvy.seek.data

import com.google.gson.annotations.SerializedName

/*
 * @Module     : com.wavvy.seek.data
 * @FileName   : MusicRankData
 * @Author     : lsj
 * @CreateTime : 2026/7/17 20:56
 * @Desc       : 
 */
data class MusicRankData (
    @SerializedName("code") val code: Int,
    @SerializedName("playlist") val playlist: Playlist,
    )
//歌单
data class Playlist(
    @SerializedName("tracks") val tracks: List<Tracks>,
    @SerializedName("name") val name:String
)
//歌曲
data class Tracks(
    @SerializedName("name") val name:String,
    @SerializedName("id") val id:String
)