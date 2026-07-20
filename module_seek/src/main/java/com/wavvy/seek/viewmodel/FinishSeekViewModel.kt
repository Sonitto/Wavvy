package com.wavvy.seek.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wavvy.seek.data.MvData
import com.wavvy.seek.data.SeekLyricData
import com.wavvy.seek.data.SeekSingerData
import com.wavvy.seek.data.SingleData
import com.wavvy.seek.data.SongListData
import com.wavvy.seek.model.SeekResultModel
import kotlinx.coroutines.launch

/*
 * @Module     : com.wavvy.seek.viewmodel
 * @FileName   : SeekResultViewModel
 * @Author     : lsj
 * @CreateTime : 2026/7/19 19:24
 * @Desc       : 
 */
class SeekResultViewModel: ViewModel() {
    private val seekResultModel= SeekResultModel()
    //单曲
    private val _singleData= MutableLiveData<SingleData>()
    val singleData: LiveData<SingleData> = _singleData
    //歌单
    private val _songList= MutableLiveData<SongListData>()
    val songData: LiveData<SongListData> =_songList

    private val _lyricData= MutableLiveData<SeekLyricData>()
    val lyricData: LiveData<SeekLyricData> =_lyricData

    private val _singerData= MutableLiveData<SeekSingerData>()
    val singerData: LiveData<SeekSingerData> =_singerData

    private val _mvData= MutableLiveData<MvData>()
    val mvData: LiveData<MvData> =_mvData

    fun upSingleData(keyword: String){
        viewModelScope.launch {
            val result=seekResultModel.fetchSingleData(keyword)
            result.onSuccess { data ->
                _singleData.value=data
            }
            result.onFailure { exception ->
                Log.d("网络请求失败", exception.toString())
            }
        }
    }

    fun upSingerData(keyword: String){
        viewModelScope.launch {
            val result=seekResultModel.fetchSingerData(keyword)
            result.onSuccess { data ->
                _singerData.value=data
            }
            result.onFailure { exception ->
                Log.d("网络请求失败", exception.toString())
            }

        }
    }
    fun upMvData(keyword: String){
        viewModelScope.launch {
            val result=seekResultModel.fetchMvData(keyword)
            result.onSuccess { data ->
                _mvData.value=data
            }
            result.onFailure { exception ->
                Log.d("网络请求失败", exception.toString())
            }
        }
    }
    fun upLyricData(keyword: String){
        viewModelScope.launch {
            val result=seekResultModel.fetchLyricData(keyword)
            result.onSuccess { data ->
                _lyricData.value=data
            }
            result.onFailure { exception ->
                Log.d("网络请求失败", exception.toString())
            }

        }
    }
    fun upSongListData(keyword: String){
        viewModelScope.launch {
            val result=seekResultModel.fetchSongListData(keyword)
            result.onSuccess { data ->
                _songList.value=data
            }
            result.onFailure { exception ->
                Log.d("网络请求失败", exception.toString())
            }
        }
    }


}
