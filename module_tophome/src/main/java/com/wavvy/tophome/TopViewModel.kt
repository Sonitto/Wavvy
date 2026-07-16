package com.wavvy.tophome

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val _bannerData= MutableLiveData<BannerData>()
    val bannerData: LiveData<BannerData> =_bannerData
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
}