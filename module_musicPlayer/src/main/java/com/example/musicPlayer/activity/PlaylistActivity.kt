package com.example.musicPlayer.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.lib.common.BaseActivity
import com.example.lib.route.RoutePath

import com.example.module.musicplayer.R
import com.example.module.musicplayer.databinding.ActivityPlaylistBinding

@Route(path = RoutePath.PLAYLIST)
class PlaylistActivity : BaseActivity<ActivityPlaylistBinding>() {
    val type: String?=null
    override fun getViewBinding(): ActivityPlaylistBinding=ActivityPlaylistBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ARouter.getInstance().inject(this)
    }
    //通过传递的type决定是哪个页面
    override fun initEvent() {
        val fragment=when(type){
            "like" -> LikeFragment()
            else -> HistoryFragment()
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}