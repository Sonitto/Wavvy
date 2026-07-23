package com.example.musicPlayer.util

import android.content.Context
import android.content.SharedPreferences

/**
 * description :管理用户uid
 * author : Cherry77551
 * date : 2026/7/22 20:02
 */
object UidManager {
    private val PREFS_NAME="user_uid"
    private val KEY_UID="uid"
    private var prefs: SharedPreferences? = null

    fun init(context: Context) {
        if (prefs != null) return
        prefs = context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
    //防空 不写这个逻辑会报错。
    private fun requirePrefs(): SharedPreferences {
        return prefs ?: throw IllegalStateException("UidManager.init(context) must be called first")
    }

    fun saveUid(uid: Long) {
        requirePrefs().edit().putLong(KEY_UID, uid).apply()
    }

    fun getUid(): Long {
        return requirePrefs().getLong(KEY_UID, 0L)
    }

    fun clearUid() {
        requirePrefs().edit().remove(KEY_UID).apply()
    }


}