package com.wavvy.seek.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.lib.common.BaseActivity
import com.example.module_seek.R
import com.example.module_seek.databinding.ActivitySeekResultBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.wavvy.seek.adapter.SeekResultVp2Adapter

class SeekResultActivity : BaseActivity<ActivitySeekResultBinding>(){
    var keyword: String? = null
    private var mediator: TabLayoutMediator? = null
    private val tabTitles = arrayOf("歌词", "单曲", "歌手", "mv", "歌单")
    private val seekResultVp2Adapter by lazy {
        SeekResultVp2Adapter(this,keyword)
    }
    override fun getViewBinding(): ActivitySeekResultBinding {
        return ActivitySeekResultBinding.inflate(layoutInflater)
    }

    override fun initEvent() {
      initView()
    }
    fun initView(){
        //vp2
        binding.vp2ResultSeek.adapter= seekResultVp2Adapter
        binding.vp2ResultSeek.offscreenPageLimit = 2
        mediator =
            TabLayoutMediator(binding.tablelayoutSeekResult, binding.vp2ResultSeek) { tab, pos ->
                tab.text = tabTitles[pos]
            }
        mediator?.attach()

    }
    override fun onDestroy() {
        super.onDestroy()
        mediator?.detach()
    }

}