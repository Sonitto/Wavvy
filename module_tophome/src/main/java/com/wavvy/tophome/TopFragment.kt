package com.wavvy.tophome

import android.R.attr.banner
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.example.lib.common.BaseFragment
import com.example.lib.route.RoutePath
import com.example.module_tophome.R
import com.example.module_tophome.databinding.FragmentTopBinding
import com.wavvy.tophome.adapter.BannerAdapter
import com.wavvy.tophome.adapter.LoveSongAdapter
import com.wavvy.tophome.adapter.SongSeaAdapter
import com.wavvy.tophome.data.BannerList
import com.wavvy.tophome.viewmodel.TopViewModel
import okhttp3.internal.http2.Http2Reader

@Route(path = RoutePath.FRAG_TOP)
class TopFragment : BaseFragment<FragmentTopBinding>() {

    private val handle= Handler(Looper.getMainLooper())
    private val scrollRunnable = object : Runnable{
        override fun run() {
            val dataList = bannerAdapter.currentList
            val listSize = dataList.size
            if (listSize>0){
                val vp=binding.vp2Banner
                vp.currentItem=vp.currentItem+1
                handle.postDelayed(this, 3000)
            }

        }
    }

    override fun getViewBinding(): FragmentTopBinding {
        return  FragmentTopBinding.inflate(layoutInflater)
    }
    override fun initEvent() {
        initView()
        bannerAdapter.onItemClick = { bannerList ->
            Log.d("banner_click", "点击触发，url = ${bannerList.url}")
            val targetPath = RoutePath.WEB
            Log.e("ROUTE_TEST", "【目标跳转路径】$targetPath")
            // 替换为带 NavigationCallback 监听的跳转
            ARouter.getInstance()
                .build(RoutePath.WEB)
                .withString("url", bannerList.url)
                .navigation(requireContext(), object : NavigationCallback {

                    override fun onFound(postcard: Postcard?) {
                        Log.d("ARouter_debug", "【找到路由】: ${postcard?.path}")
                    }

                    override fun onLost(postcard: Postcard?) {
                        // ⚠️ 如果控制台输出了这一行，代表 ARouter 的全局路由表内没有这个页面
                        Log.e("ARouter_debug", "【失败】找不到路由节点，说明注册失效了！")
                    }

                    override fun onArrival(postcard: Postcard?) {
                        // ⚠️ 如果控制台输出了这一行，代表 ARouter 已经成功发起跳转，但 Activity 没打开
                        Log.d("ARouter_debug", "【成功】ARouter 已经向系统发送了跳转指令")
                    }

                    override fun onInterrupt(postcard: Postcard?) {
                        Log.e("ARouter_debug", "【拦截】跳转被拦截器拦截")
                    }
                })
        }
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[TopViewModel::class.java]
    }
    private val bannerAdapter by lazy { BannerAdapter(requireContext()) }
    private val loveSongAdapter by lazy {
        LoveSongAdapter(requireContext())
    }
    private val songAdapter by lazy {
        SongSeaAdapter(requireContext())
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
            loveSongData.observe(this@TopFragment){
                loveSongAdapter.submitList(it.result)
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
        viewModel.upLoveSongData()
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

//页面可见开启轮播
    override fun onResume() {
        super.onResume()
        startAutoScroll()
    }

    //页面销毁的时候情空
    override fun onDestroyView() {
        super.onDestroyView()
        stopAutoScroll()
        handle.removeCallbacksAndMessages(null)
    }


}