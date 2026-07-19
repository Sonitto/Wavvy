package com.example.module.login.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.module.login.repo.GuestRepository
import com.wavvy.net.CookieManager
import com.wavvy.net.CookieUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * description :
 * author : Cherry77551
 * date : 2026/7/17 17:45
 */
class GuestViewModel : ViewModel(){
    private val repo= GuestRepository()
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> get() = _status

    //登陆状态成功？失败
    private val _loginSuccess = MutableStateFlow<Boolean>(false)
    val loginSuccess: StateFlow<Boolean> get() = _loginSuccess

    fun guestLogin() {
        viewModelScope.launch {
            try {
                _status.value = "正在登录..."
                val response = repo.guestLogin()
                if (response.code == 200) {
                    response.cookie?.let { rawCookie ->
                        CookieUtil.cleanCookie(rawCookie)?.let { cleanCookie ->
                            CookieManager.saveCookie(cleanCookie)
                        }
                    }
                    _status.value = "登录成功"
                    _loginSuccess.value = true
                } else {
                    _status.value = "登录失败：${response.code}"
                }
            } catch (e: Exception) {
                _status.value = "出错了：${e.message}"
            }
        }
    }
}
