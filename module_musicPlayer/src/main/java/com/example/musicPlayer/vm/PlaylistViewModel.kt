package com.example.musicPlayer.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicPlayer.data.Song
import com.example.musicPlayer.data.Record
import com.example.musicPlayer.repository.PlaylistRepository
import kotlinx.coroutines.launch

class PlaylistViewModel: ViewModel() {
    private val repo= PlaylistRepository()
    private val _songs = MutableLiveData<List<Song>>()
    val songs: LiveData<List<Song>> get() = _songs

    private val _records = MutableLiveData<List<Record>>()
    val records: LiveData<List<Record>> get() = _records

    fun loadLikeSongs(uid: Long) {
        viewModelScope.launch {
            _songs.value = repo.getLikeSongs(uid)
        }
    }

    fun loadHistory(uid: Long) {
        viewModelScope.launch {
            _records.value = repo.getRecord(uid)
        }
    }

}