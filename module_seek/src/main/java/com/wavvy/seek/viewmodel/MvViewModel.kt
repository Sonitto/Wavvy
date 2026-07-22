package com.wavvy.seek.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wavvy.seek.data.MvUrlData
import com.wavvy.seek.model.MvModel
import kotlinx.coroutines.launch

/*
 * @Module     : com.wavvy.seek.viewmodel
 * @FileName   : MvViewModel
 * @Author     : lsj
 * @CreateTime : 2026/7/21 20:55
 * @Desc       : 
 */
class MvViewModel: ViewModel() {
    val mvModel: MvModel= MvModel()
    private val _mvUrlData= MutableLiveData<MvUrlData>()
    val mvUrlData: LiveData<MvUrlData> =_mvUrlData

    fun upMvUrl(id: Long){
        viewModelScope.launch {
            val result=mvModel.fetchMvURLData(id)
            result.onSuccess { data ->
                _mvUrlData.value=data
            }
            result.onFailure { exception ->
                Log.d("网络请求失败", exception.toString())
            }
        }
    }
}