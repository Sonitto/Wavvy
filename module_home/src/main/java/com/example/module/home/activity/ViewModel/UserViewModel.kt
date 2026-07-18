package com.example.module.home.activity.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.util.Log
import com.example.module.home.activity.data.Profile
import com.example.module.home.activity.data.UserDetail
import com.example.module.home.activity.repository.InfoRepository
import kotlinx.coroutines.launch

/**
 * description :
 * author : Cherry77551
 * date : 2026/7/17 23:21
 */
class UserViewModel: ViewModel() {
    private val repo= InfoRepository()

    private val _profile= MutableLiveData<Profile>()
    val profile: LiveData<Profile> get() = _profile

    fun loadInfo(){
        viewModelScope.launch{
            try {
                val accountRes = repo.getAccount()
                Log.d("UserInfo", "accountRes: $accountRes")
                val account = accountRes.account
                val uid = account?.id
                if(uid == null) {
                    Log.d("UserInfo", "uid is null, code=${accountRes.code}")
                    return@launch
                }
                val detailRes = repo.getDetail(uid)
                Log.d("UserInfo", "detailRes: $detailRes")
                val profile = detailRes.profile
                if(profile != null) {
                    _profile.value = profile
                    Log.d("UserInfo", "profile loaded: $profile")
                } else {
                    Log.d("UserInfo", "profile is null")
                }
            } catch (e: Exception) {
                Log.e("UserInfo", "loadInfo error", e)
            }
        }
    }
}