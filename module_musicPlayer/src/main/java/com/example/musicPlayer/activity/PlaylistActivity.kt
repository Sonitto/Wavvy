package com.example.musicPlayer.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.example.lib.common.BaseActivity

import com.example.module.musicplayer.R
import com.example.module.musicplayer.databinding.ActivityPlaylistBinding

class PlaylistActivity : BaseActivity<ActivityPlaylistBinding>() {
    override fun getViewBinding(): ActivityPlaylistBinding = ActivityPlaylistBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val type = intent.getStringExtra("type")
        val fragment = when (type) {
            "like" -> LikeFragment()
            else -> HistoryFragment()
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun initEvent() {
    }
}