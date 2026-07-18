package com.wavvy.seek.data

import com.google.gson.annotations.SerializedName

/*
 * @Module     : com.wavvy.seek.data
 * @FileName   : SongListData
 * @Author     : lsj
 * @CreateTime : 2026/7/17 21:36
 * @Desc       : 歌单
 */
data class SongListData (
    @SerializedName("code") val code: Int,
    @SerializedName("result") val result: ResultPlaylist
)
data class ResultPlaylist(
    val playlists: List<Playlists>,
)
data class Playlists(
    @SerializedName("coverImgUrl") val coverImgUrl: String,
    @SerializedName("creator") val creator: Creator,
    @SerializedName("description") val description: String,
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("userId") val userId: Long,
    @SerializedName("trackCount") val trackCount: Int
)
data class Creator(
    @SerializedName("avatarUrl") val avatarUrl: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("userId") val userId: Long,
)
