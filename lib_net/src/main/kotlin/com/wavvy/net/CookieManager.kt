package com.wavvy.net

import android.content.Context
import android.content.SharedPreferences

/**
 * description :一个cookie管理类
 * author : Cherry77551
 * date : 2026/7/17 21:46
 */
object CookieManager {
    private const val PREFS_NAME = "wavvy_cookie"
    private const val KEY_COOKIE = "cookie"

    //@Volatile保证多线程读写时，所有线程看到的都是最新值
    @Volatile
    private var prefs: SharedPreferences? = null
    //在application里面初始化
    fun init(context: Context) {
        if (prefs != null) return
        prefs = context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
    //确保其他方法调用前，CookieManager已经初始化
    private fun requirePrefs(): SharedPreferences {
        return prefs ?: throw IllegalStateException("CookieManager.init(context) must be called first")
    }
    //将cookie存入
    fun saveCookie(cookie: String?) {
        if (cookie.isNullOrBlank()) return
        requirePrefs().edit().putString(KEY_COOKIE, cookie).apply()
    }
    //通过键获取cookie
    fun getCookie(): String? {
        return requirePrefs().getString(KEY_COOKIE, null)
    }

    fun clearCookie() {
        requirePrefs().edit().remove(KEY_COOKIE).apply()
    }
}
