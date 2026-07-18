package com.wavvy.seek

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wavvy.seek.data.MusicRankData
import kotlinx.coroutines.launch

/*
 * @Module     : com.wavvy.seek
 * @FileName   : SeekViewModel
 * @Author     : lsj
 * @CreateTime : 2026/7/18 14:36
 * @Desc       : 
 */
class SeekViewModel: ViewModel() {
    private val seekModel: SeekModel= SeekModel()
    //排行榜
    private val _musicRankDataList= MutableLiveData<List<MusicRankData>>()
    val musicRankDataList: LiveData<List<MusicRankData>> =_musicRankDataList
    fun upMusicRank(){
        viewModelScope.launch {
            val list=mutableListOf<MusicRankData>()
            seekModel.fetchMusicRank().collect { data->
                list.add(data)
                _musicRankDataList.postValue(list.toList())
            }
        }
    }
}