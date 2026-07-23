package com.example.musicPlayer.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.example.lib.common.BaseActivity
import com.example.lib.route.RoutePath
import com.example.module.musicplayer.R
import com.example.module.musicplayer.databinding.ActivityPlayerBinding
import com.example.musicPlayer.data.Song
import com.example.musicPlayer.util.MusicService
import com.google.android.material.slider.Slider

@Route(path= RoutePath.PLAYER)
class PlayerActivity : BaseActivity<ActivityPlayerBinding>() {
    //标记用户是否在拖拽
    private var isDragging=false
    private var currentSong: Song?=null
    override fun getViewBinding(): ActivityPlayerBinding = ActivityPlayerBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val song = intent.getParcelableExtra<Song>("song")
        if (song != null) {
            updateSongInfo(song)
        }

        registerReceiver(songChangeReceiver, IntentFilter(MusicService.BROADCAST_SONG_CHANGE))
        registerReceiver(progressReceiver, IntentFilter(MusicService.BROADCAST_PROGRESS))
    }

    private fun updateSongInfo(song: Song) {
        currentSong = song
        binding.tvSong.text = song.name
        binding.tvArtist.text = song.artistNames()
        Glide.with(this)
            .load(song.coverUrl())
            .placeholder(R.drawable.ic_default_cover)
            .into(binding.imgCover)
    }

    override fun initEvent() {
        //按钮与广播绑定
        binding.btnStart.setOnClickListener {
            MusicService.action?.invoke(MusicService.ACTION_PLAY_PAUSE)
        }
        binding.btnNext.setOnClickListener {
            MusicService.action?.invoke(MusicService.ACTION_NEXT)
        }
        binding.btnPrev.setOnClickListener {
            MusicService.action?.invoke(MusicService.ACTION_PREV)
        }
        binding.btnExit.setOnClickListener { finish() }
        //进度条拖拽
        binding.seekBar.addOnSliderTouchListener(object : Slider.OnSliderTouchListener{
            override fun onStartTrackingTouch(slider: Slider) {
               isDragging=true
            }
        //发送seek:位置指令到 Service，跳转到指定时间点
            override fun onStopTrackingTouch(slider: Slider) {
                isDragging=false
                MusicService.action?.invoke("seek:${slider.value.toLong()}")
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(songChangeReceiver)
        unregisterReceiver(progressReceiver)
    }
    private val songChangeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val song = intent?.getParcelableExtra<Song>("song") ?: return
            updateSongInfo(song)
        }
    }
    //处理进度广播
    private val progressReceiver=object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent) {
            //从广播里取出当前进度、总时长、播放状态
            val position=intent.getLongExtra("position",0)
            val duration=intent.getLongExtra("duration",0)
            val isPlaying=intent.getBooleanExtra("isPlaying",false)
            if (duration > 0 && !isDragging) {
                val clamped = minOf(position, duration)
                binding.seekBar.valueTo = duration.toFloat()
                binding.seekBar.value = clamped.toFloat()
                binding.tvCurrentTime.text = formatDuration(clamped)
                binding.tvTotalTime.text = formatDuration(duration)
            }
            //根据播放状态切换按钮图标
            binding.btnStart.setBackgroundResource(
                if (isPlaying) R.drawable.ic_pause else R.drawable.ic_start
            )
        }
    }
    //格式化时长
    private fun formatDuration(ms: Long): String {
        val sec = (ms / 1000).toInt()
        return "${sec / 60}:${String.format("%02d", sec % 60)}"
    }
}