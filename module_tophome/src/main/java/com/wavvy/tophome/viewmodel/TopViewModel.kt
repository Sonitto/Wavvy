package com.wavvy.tophome.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wavvy.tophome.data.BannerData
import com.wavvy.tophome.data.DailySongList
import com.wavvy.tophome.model.TopModel
import kotlinx.coroutines.launch

/*
 * @Module     : com.wavvy.tophome
 * @FileName   : TopViewModel
 * @Author     : lsj
 * @CreateTime : 2026/7/16 15:20
 * @Desc       :
 */
class TopViewModel: ViewModel(){
    private val topModel= TopModel()
    //轮播图
    private val _bannerData= MutableLiveData<BannerData>()
    val bannerData: LiveData<BannerData> =_bannerData
    //多元旋律海洋
    private val _recommendSong= MutableLiveData<List<List<DailySongList>>>()
    val recommendData: LiveData<List<List<DailySongList>>> =_recommendSong

    fun upBannerData(){
        viewModelScope.launch {
            val result=topModel.fetchBannerData()
            result.onSuccess { bannerData ->
                _bannerData.value=bannerData
            }
            result.onFailure { exception ->
                Log.d("网络请求失败", exception.toString())
            }
        }
    }
    fun upRecommendSong(){
        viewModelScope.launch {
            val result=topModel.fetchRecommendSongs()
            result.onSuccess { songData->
               val songsList=songData.data.dailySongs
                _recommendSong.value=songsList.chunked(3)
            }
            result.onFailure { exception ->
                Log.d("网络请求失败", exception.toString())
            }
        }
    }
}