package com.example.lib.common

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.viewbinding.ViewBinding
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

/**
 * description ： Activity的基础类
 * author : HI-IR
 * email : qq2420226433@outlook.com
 * date : 2025/7/13 09:58
 */
abstract class BaseActivity<VB : ViewBinding>: AppCompatActivity() {
    protected val binding: VB by lazy {
        getViewBinding()
    }

    //用于获取ViewBinding
    protected abstract fun getViewBinding(): VB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        doTransparentTaskbar()

        initEvent()

        if (!isInternetAvailable(this)) {
            Toast.makeText(this, "请检查网络", Toast.LENGTH_SHORT).show()
        }
    }


    //沉浸式任务栏
    private fun doTransparentTaskbar() {
        val window = this.window
        val decorView = window.decorView
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val windowInsetsController = WindowCompat.getInsetsController(window, decorView)
        windowInsetsController.isAppearanceLightStatusBars = true
        window.statusBarColor = Color.TRANSPARENT //把状态栏颜色设置成透明
    }


    /**
     * 注册点击事件
     */
    abstract fun initEvent()


    //检查手机网络连接情况
    fun isInternetAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = cm.activeNetwork ?: return false
        val capabilities = cm.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}