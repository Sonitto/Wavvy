package com.example.wavvy

import android.app.Application
import com.alibaba.android.arouter.BuildConfig
import com.alibaba.android.arouter.launcher.ARouter
import com.example.musicPlayer.util.UidManager
import com.wavvy.net.CookieManager

/**
 * description :初始化ARouter
 * author : Cherry77551
 * date : 2026/7/16 17:14
 */
class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
        CookieManager.init(this)
        UidManager.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        ARouter.getInstance().destroy()
    }
}
