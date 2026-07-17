package com.example.module.login.vm

import android.util.Base64
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.module.login.repo.QrRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


/**
 * description :
 * author : Cherry77551
 * date : 2026/7/16 22:14
 */
class QrViewModel : ViewModel() {
    private val repository = QrRepository()

    //二维码状态文字
    private val _status = MutableStateFlow<String>("二维码正在生成中……")
    val status: StateFlow<String> get() = _status

    //二维码图片字节
    private val _qrBytes = MutableStateFlow<ByteArray?>(null)
    val qrBytes: StateFlow<ByteArray?> get() = _qrBytes

    //登陆状态成功？失败
    private val _loginSuccess = MutableStateFlow<Boolean>(false)
    val loginSuccess: StateFlow<Boolean> get() = _loginSuccess

    //开始扫码
    fun startQr() {
        viewModelScope.launch {
            try {
                _status.value = "二维码正在生成中……"
                val key = repository.getQrKey().data.unikey
                val qrBase = repository.createQr(key).data.qrimg
                Log.d("QrDebug", "qring 长度: ${qrBase?.length}")
                Log.d("QrDebug", "qring 前50字符: ${qrBase?.take(50)}")
                //把文本解码成原始的字节数组，然后交给 Glide 显示成图片
                if (qrBase != null) {
                    _qrBytes.value = Base64.decode(qrBase.substringAfter(","), Base64.DEFAULT)
                }//substringAfter(",") 就是把逗号前面的去掉，只留后面的纯 base64
                _status.value = "请扫码"
                dealQr(key)
            } catch (e: Exception) {
                _status.value = "出错了，${e.message}"
            }
        }
    }
    //处理扫码结果
    fun dealQr(key: String) {
        viewModelScope.launch {
            try {
                while (true) {
                    delay(30000)
                    val response = repository.checkQr(key).code
                    when (response) {
                        800 -> {
                            _status.value="二维码已过期，请重试"
                            startQr()
                        }
                        801 -> _status.value="等待扫码中…"
                        802 -> _status.value="待确认…"
                        803 -> {_status.value="登陆成功"
                            _loginSuccess.value=true
                        }
                    }

                }
            } catch (e: Exception){
                    _status.value = "出错了，${e.message}"
                }
            }
        }
    }
