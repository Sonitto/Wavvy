package com.wavvy.seek.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.lib.common.BaseActivity
import com.example.lib.route.RoutePath
import com.example.module_seek.databinding.ActivitySeekBinding
import com.wavvy.seek.adapter.MusicRankAdapter
import com.wavvy.seek.viewmodel.SeekViewModel
@Route(path = RoutePath.SEEK)
class SeekActivity : BaseActivity<ActivitySeekBinding>(){
    private val viewModel by lazy {
        ViewModelProvider(this)[SeekViewModel::class.java]
    }
    private val rankVp2Adapter by lazy {
        MusicRankAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun getViewBinding(): ActivitySeekBinding {
       return ActivitySeekBinding.inflate(layoutInflater)
    }

    override fun initEvent() {
       initView()
        observeData()
        viewModel.upMusicRank()
    }
    fun observeData(){
        viewModel.musicRankDataList.observe(this@SeekActivity){rankDataList->
            val playlistList = rankDataList.map { data -> data.playlist }
            rankVp2Adapter.submitList(playlistList)
        }

    }
    private fun initView(){
        binding.apply {
            vp2RecommendMusic.adapter=rankVp2Adapter

            //
            tvSeek.setOnClickListener {
                val keyword=binding.etSeek.text?.toString()
                ARouter.getInstance().build(RoutePath.FINISH)
                    .withString("keyword",keyword)
                    .navigation()
            }

        }

    }
    fun seekHistory(){

    }

}