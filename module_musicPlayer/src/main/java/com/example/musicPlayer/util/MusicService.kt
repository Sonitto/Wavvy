package com.example.musicPlayer.util

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import android.os.IBinder
import androidx.core.app.NotificationCompat

import androidx.media3.common.MediaItem

import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.bumptech.glide.Glide
import com.example.module.musicplayer.R
import com.example.musicPlayer.data.Song
import com.example.musicPlayer.repository.MusicRepository
import kotlinx.coroutines.*
import java.util.concurrent.Executors

/**
 * description :
 * author : Cherry77551
 * date : 2026/7/20 22:02
 */
class MusicService: Service() {
    //通知渠道id与编号
    private val channelId = "music_channel"
    private val notificationId = 1
    private val repo = MusicRepository()

    private lateinit var player: ExoPlayer
    private val receiver = MusicBroadcastReceiver()
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    //单线程线程池
    private val executor = Executors.newSingleThreadExecutor()

    //播放歌曲列表和当前歌曲的位置
    private var currentSong: Song? = null
    private var songList: List<Song> = emptyList()
    private var currentIndex = -1
    private var progressJob: Job? = null

    companion object {
        const val ACTION_PLAY = "com.example.module.musicPlayer.PLAY"
        const val ACTION_PLAY_INDEX = "com.example.module.musicPlayer.PLAY_INDEX"
        const val ACTION_PLAY_PAUSE = "com.example.module.musicPlayer.PLAY_PAUSE"
        const val ACTION_NEXT = "com.example.module.musicPlayer.NEXT"
        const val ACTION_PREV = "com.example.module.musicPlayer.PREV"
        const val ACTION_STOP = "com.example.module.musicPlayer.STOP"
        const val BROADCAST_SONG_CHANGE = "com.example.module.musicPlayer.SONG_CHANGE"
        const val BROADCAST_PROGRESS = "com.example.module.musicPlayer.PROGRESS"

        var action: ((String) -> Unit)? = null
        fun play(context: Context, song: Song, list: List<Song>) {
            val intent = Intent(context, MusicService::class.java).apply {
                action = ACTION_PLAY
                putExtra("song", song)
                putParcelableArrayListExtra("list", ArrayList(list))
            }
            context.startForegroundService(intent)
        }
    }

    override fun onCreate() {
        super.onCreate()
        action = { act -> handleAction(act) }
        //动态注册广播接收器，监听通知栏按钮的点击
        registerReceiver(receiver, IntentFilter().apply {
            addAction(ACTION_PLAY_PAUSE)
            addAction(ACTION_NEXT)
            addAction(ACTION_PREV)
            addAction(ACTION_STOP)
        })
        //创建通知渠道
        createNotificationChannel()
        //创建player实例,一首歌结束后播放下一首
        player = ExoPlayer.Builder(this).build()
        player.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_ENDED) {
                    playNext()
                }
            }
        })
        startForeground(notificationId, buildNotification(null, "准备播放", ""))
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_PLAY -> {
                val song = intent.getParcelableExtra<Song>("song")
                val list = intent.getParcelableArrayListExtra<Song>("list")?.toList()
                if (song != null && list != null) {
                    songList = list
                    currentIndex = list.indexOf(song)
                    playSong(song)
                }
            }

            ACTION_PLAY_INDEX -> {
                val index = intent.getIntExtra("index", -1)
                val list = intent.getParcelableArrayListExtra<Song>("list")?.toList()
                if (index >= 0 && list != null) {
                    songList = list
                    currentIndex = index
                    playSong(songList[currentIndex])
                }
            }
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        action = null
        unregisterReceiver(receiver)
        scope.cancel()
        executor.shutdown()
        player.release()
        super.onDestroy()
    }

    private fun playSong(song: Song) {
        currentSong = song
        scope.launch {
            try {
                val url = repo.getUrl(song.id)?.url
                if (url.isNullOrEmpty()) return@launch
                val mediaItem = MediaItem.fromUri(url)
                player.setMediaItem(mediaItem)
                player.prepare()
                player.play()
                val bitmap = withContext(Dispatchers.IO) {
                    try {
                        Glide.with(this@MusicService)
                            .asBitmap()
                            .load(song.coverUrl())
                            .submit(300, 300)
                            .get()
                    } catch (e: Exception) {
                        null
                    }
                }
                updateNotification(bitmap, song.name, song.artistNames())
                sendBroadcast(Intent(BROADCAST_PROGRESS).apply {
                    putExtra("position", player.currentPosition)
                    putExtra("duration", player.duration)
                    putExtra("isPlaying", player.isPlaying)
                })
            } catch (e: Exception) {
                Log.e("MusicService", "playSong failed: ${song.id} ${song.name}", e)
            }
        }
        sendBroadcast(Intent(BROADCAST_SONG_CHANGE).putExtra("song", song))
        startProgressUpdates()
    }
    //更新通知栏
    private fun updateNotification(bitmap: Bitmap?, title: String, subtitle: String) {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationId, buildNotification(bitmap, title, subtitle))
    }

    //通知栏建构
    private fun buildNotification(bitmap: Bitmap?, title: String, subtitle: String): Notification {
        //构建PendingIntent时需要的flag
        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        else PendingIntent.FLAG_UPDATE_CURRENT
        //对应4个按钮：播放/暂停、下一首、上一首、停止
        val playPause = PendingIntent.getBroadcast(this, 0, Intent(ACTION_PLAY_PAUSE), flags)
        val next = PendingIntent.getBroadcast(this, 1, Intent(ACTION_NEXT), flags)
        val prev = PendingIntent.getBroadcast(this, 2, Intent(ACTION_PREV), flags)
        val stop = PendingIntent.getBroadcast(this, 3, Intent(ACTION_STOP), flags)
    return NotificationCompat.Builder(this,channelId)
        .setSmallIcon(R.drawable.ic_cd)
        .setContentTitle(title)
        .setContentText(subtitle)
        .setLargeIcon(bitmap)
        //折叠状态下显示前三个个按钮
        .setStyle(androidx.media.app.NotificationCompat.MediaStyle().setShowActionsInCompactView(0,1,2))
        //添加通知上的按钮
        .addAction(R.drawable.ic_prev,"上一首",prev)
        .addAction(
            if(player.isPlaying) R.drawable.ic_pause else R.drawable.ic_start,
            if(player.isPlaying) {
                "暂停"
            } else {
                "开始"
            },
            playPause
        )
        .addAction(R.drawable.ic_next,"下一首",next)
        .setDeleteIntent(stop)
        //滑动可否清楚
        .setOngoing(player.isPlaying)
        //锁屏显示
        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        .build()
    }
    //播放下一首
    private fun playNext(){
        if (songList.isEmpty()) return
        currentIndex = (currentIndex+1) % songList.size
        playSong(songList[currentIndex])
    }
    //播放上一首
    private fun playPrev(){
        if (songList.isEmpty()) return
        currentIndex = (currentIndex-1) % songList.size
        playSong(songList[currentIndex])
    }
    //处理act
    private fun handleAction(act: String){
        when(act){
            ACTION_PLAY_PAUSE -> {
                if (player.isPlaying) {
                    player.pause()
                    stopProgressUpdates()
                } else {
                    player.play()
                    startProgressUpdates()
                }
                sendBroadcast(Intent(BROADCAST_PROGRESS).apply {
                    putExtra("position", player.currentPosition)
                    putExtra("duration", player.duration)
                    putExtra("isPlaying", player.isPlaying)
                })
                currentSong?.let { it ->
                    scope.launch {
                        val bitmap = withContext(Dispatchers.IO) {
                            try {
                                Glide.with(this@MusicService)
                                    .asBitmap()
                                    .load(it.coverUrl())
                                    .submit(300, 300)
                                    .get()
                            } catch (e: Exception) {
                                null
                            }
                        }
                        updateNotification(bitmap, it.name, it.artistNames())
                    }
                }
            }
            ACTION_NEXT -> playNext()
            ACTION_PREV ->playPrev()
            ACTION_STOP ->{
                player.stop()
                stopForeground(STOP_FOREGROUND_REMOVE)
                stopSelf()
            }
            else -> {
            val prefix = "seek:"
            if (act.startsWith(prefix)) {
                act.removePrefix(prefix).toLongOrNull()?.let { player.seekTo(it) }
            }
        }
        }
    }
    //创建通知渠道
    private fun createNotificationChannel(){
        //获取系统的通知管理服务强制转换成 NotificationManager 对象
        (getSystemService(NOTIFICATION_SERVICE) as NotificationManager)
            .createNotificationChannel(
                NotificationChannel(channelId,
                "音乐播放",
                NotificationManager.IMPORTANCE_LOW //通知优先级低
            ).apply { setShowBadge(false) })//不在应用图标上显示小红点/角标
    }
    private fun startProgressUpdates() {
        progressJob?.cancel()
        progressJob = scope.launch {
            while (isActive) {
                val intent = Intent(BROADCAST_PROGRESS).apply {
                    putExtra("position", player.currentPosition)
                    putExtra("duration", player.duration)
                    putExtra("isPlaying", player.isPlaying)
                }
                sendBroadcast(intent)
                delay(500)
            }
        }
    }

    private fun stopProgressUpdates() {
        progressJob?.cancel()
    }
}

