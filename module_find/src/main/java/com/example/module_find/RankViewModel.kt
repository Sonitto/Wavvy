package com.example.module_find

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.module_find.data.RankData
import kotlinx.coroutines.launch

/*
 * @Module     : com.example.module_find
 * @FileName   : RankViewModel
 * @Author     : lsj
 * @CreateTime : 2026/7/20 22:08
 * @Desc       : 
 */
class RankViewModel: ViewModel() {
    private val rankModel= FindModel()
    private val _rankData= MutableLiveData<RankData>()
    val rankData: LiveData<RankData> =_rankData
    fun upRankData(){
        viewModelScope.launch {
            val result=rankModel.fetchRankData()
            result.onSuccess { data ->
                _rankData.value=data
            }
            result.onFailure { exception ->
                Log.d("网络请求失败", exception.toString())
            }
        }
    }
}