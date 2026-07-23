package com.wavvy.seek.view

import android.os.Bundle
import androidx.media3.exoplayer.ExoPlayer
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lib.common.BaseActivity
import com.example.lib.route.RoutePath
import com.example.module_seek.databinding.ActivityMvPlayBinding


@Route(path = RoutePath.MV)
class MvPlayActivity : BaseActivity<ActivityMvPlayBinding>() {
    @Autowired(name = "id")
    var id: Long? = null
    private lateinit var player: ExoPlayer
    override fun getViewBinding(): ActivityMvPlayBinding {
        return ActivityMvPlayBinding.inflate(layoutInflater)
    }

    override fun initEvent(){
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)}
}