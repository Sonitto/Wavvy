package com.wavvy.seek.data

import com.google.gson.annotations.SerializedName

/*
 * @Module     : com.wavvy.seek.data
 * @FileName   : SeekLyricData
 * @Author     : lsj
 * @CreateTime : 2026/7/17 20:59
 * @Desc       : 歌词
 */
data class SeekLyricData(
    @SerializedName("code") val code: Int,
    @SerializedName("result") val result: ResultLyric
)

data class ResultLyric(
    @SerializedName("songCount") val songCount: Int,
    @SerializedName("songs") val songs: List<SongLyric>
)

data class SongLyric(
    @SerializedName("album") val album: Album,
    @SerializedName("artists") val artists: List<Artist>,
    @SerializedName("id") val id: Long,
    @SerializedName("lyrics") val lyrics: Lyrics,
    @SerializedName("name") val name: String,
){
    fun getSinger(): String = artists.joinToString("/") { it.name }
}
//歌词
data class Lyrics(
    @SerializedName("range") val range: List<Range>,
    @SerializedName("txt") val txt: String
)
//开始时间和结束时间
data class Range(
    @SerializedName("first") val first: Int,
    @SerializedName("second") val second: Int
)
data class Album(
    @SerializedName("artist") val artist: Artist,
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("img1v1Url") val img1v1Url :String
)

data class Artist(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("img1v1Url") val img1v1Url :String
)
