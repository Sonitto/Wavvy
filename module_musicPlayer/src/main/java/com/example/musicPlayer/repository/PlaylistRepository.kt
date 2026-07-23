package com.example.musicPlayer.repository

import com.example.musicPlayer.api.PlaylistApi
import com.example.musicPlayer.data.Playlist
import com.example.musicPlayer.data.Record
import com.example.musicPlayer.data.Song
import com.wavvy.net.RetrofitClient

/**
 * description :歌单操作仓库
 * author : Cherry77551
 * date : 2026/7/20 20:48
 */
class PlaylistRepository {
    private val listApi by lazy{
        RetrofitClient.create(PlaylistApi::class.java)
    }
    //我喜欢歌单
    suspend fun getLikeList(uid: Long): Playlist? {
        return listApi.getPlaylist(uid).playlist
            ?.firstOrNull { it.specialType == 5}
    }
    suspend fun getLikeSongs(uid: Long): List<Song>{
        val like=getLikeList(uid) ?:return emptyList()
        return listApi.getDetail(like.id).songs ?:emptyList()
    }
    //播放记录
    suspend fun getRecord(uid: Long): List<Record>{
        return listApi.getRecord(uid).allData ?:emptyList()
    }
}