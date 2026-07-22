package com.example.musicPlayer.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicPlayer.data.Song
import com.example.musicPlayer.data.Record
import com.example.musicPlayer.repository.PlaylistRepository
import kotlinx.coroutines.launch

/**
 * description :歌单的vm
 * author : Cherry77551
 * date : 2026/7/21 22:35
 */
class PlaylistViewModel: ViewModel() {
    private val repo= PlaylistRepository()
    private val _songs = MutableLiveData<List<Song>>()
    val songs: LiveData<List<Song>> get() = _songs

    private val _records = MutableLiveData<List<Record>>()
    val records: LiveData<List<Record>> get() = _records

    // 加载"我喜欢的"歌曲
    fun loadLikeSongs(uid: Long) {
        viewModelScope.launch {
            _songs.value = repo.getLikeSongs(uid)
        }
    }

    // 加载播放历史
    fun loadHistory(uid: Long) {
        viewModelScope.launch {
            _records.value = repo.getRecord(uid)
        }
    }

}