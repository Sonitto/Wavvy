package com.example.musicPlayer.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.musicPlayer.util.MusicService.Companion.ACTION_NEXT
import com.example.musicPlayer.util.MusicService.Companion.ACTION_PLAY_PAUSE
import com.example.musicPlayer.util.MusicService.Companion.ACTION_PREV
import com.example.musicPlayer.util.MusicService.Companion.ACTION_STOP
/**
 * description :广播类
 * author : Cherry77551
 * date : 2026/7/21 17:01
 */
class MusicBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action){
            ACTION_PLAY_PAUSE -> MusicService.action?.invoke(ACTION_PLAY_PAUSE)
            ACTION_NEXT -> MusicService.action?.invoke(ACTION_NEXT)
            ACTION_PREV -> MusicService.action?.invoke(ACTION_PREV)
            ACTION_STOP -> MusicService.action?.invoke(ACTION_STOP)
        }

    }
}