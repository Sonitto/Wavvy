package com.example.musicPlayer.repository

import com.example.musicPlayer.api.MusicApi
import com.example.musicPlayer.data.Song
import com.example.musicPlayer.data.SongUrl
import com.wavvy.net.RetrofitClient

/**
 * description :
 * author : Cherry77551
 * date : 2026/7/20 20:47
 */
class MusicRepository {
    private val api by lazy{
        RetrofitClient.create(MusicApi::class.java)
    }
    //获取url
    suspend fun getUrl(id: Long): SongUrl?{
        return api.getUrl(id).data?.firstOrNull()
    }
    //获取歌曲信息
    suspend fun getSong(ids: List<Long>): List<Song>{
        return api.getSong(ids.joinToString(",")).songs?:emptyList()
    }
}