package com.wavvy.tophome

import android.R.attr.banner
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lib.common.BaseFragment
import com.example.lib.route.RoutePath
import com.example.module_tophome.R
import com.example.module_tophome.databinding.FragmentTopBinding
import com.wavvy.tophome.adapter.BannerAdapter
import com.wavvy.tophome.adapter.LoveSongAdapter
import com.wavvy.tophome.adapter.SongSeaAdapter
import com.wavvy.tophome.viewmodel.TopViewModel
import okhttp3.internal.http2.Http2Reader

@Route(path = RoutePath.FRAG_TOP)
class TopFragment : BaseFragment<FragmentTopBinding>() {
    private val viewModel by lazy {
        ViewModelProvider(this)[TopViewModel::class.java]
    }
    private val bannerAdapter= BannerAdapter(requireContext())
    private val loveSongAdapter by lazy {
        LoveSongAdapter(requireContext())
    }
    private val songAdapter by lazy {
        SongSeaAdapter(requireContext())
    }

    private val handle= Handler(Looper.getMainLooper())
    private val scrollRunnable = object : Runnable{
        override fun run() {
            val dataList = bannerAdapter.currentList
            val listSize = dataList.size
            if (listSize>0){
                val vp=binding.vp2Banner
                vp.currentItem=vp.currentItem+1
            }
            handle.postDelayed(this, 3000)
        }
    }

    override fun getViewBinding(): FragmentTopBinding {
        return  FragmentTopBinding.inflate(layoutInflater)
    }


    override fun initEvent() {
        TODO("Not yet implemented")
    }

    override fun observeData() {
        viewModel.apply {
            //轮播图
            bannerData.observe(this@TopFragment){
                bannerAdapter.submitList(it.bannerList)
                binding.vp2Banner.setCurrentItem(
                    Int.MAX_VALUE/2-(Int.MAX_VALUE/ 2 % bannerAdapter.currentList.size),
                    false
                )
            }
            recommendData.observe(this@TopFragment){
                songAdapter.submitList(it)
            }

        }
    }
    private fun initView(){
        binding.apply {
            //轮播图
            vp2Banner.adapter=bannerAdapter
            //旋律海洋
            vp2SongSea.adapter=songAdapter
            vp2SongSea.offscreenPageLimit=2
            //网友爱听
            rvLoved.adapter=loveSongAdapter
            rvLoved.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        }
        viewModel.upBannerData()
        viewModel.upRecommendSong()
    }

    private fun startAutoScroll(){
        handle.removeCallbacks (scrollRunnable )
        handle.postDelayed(scrollRunnable,3000)
    }
    private fun stopAutoScroll() {
        handle.removeCallbacks(scrollRunnable)
    }



}