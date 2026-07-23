package com.example.module.home.activity

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.lib.common.BaseActivity
import com.example.lib.route.RoutePath
import com.example.module.home.activity.ViewModel.UserViewModel
import com.example.module.home.databinding.ActivityHomeBinding
import com.example.module.home.R
import com.bumptech.glide.Glide
import com.example.module.home.activity.adapter.HomeVPAdapter
import com.example.module_find.BlankFragment
import com.wavvy.tophome.TopFragment

@Route(path = RoutePath.HOME)
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    private val userVm: UserViewModel by viewModels()
    override fun getViewBinding(): ActivityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //强制使导航栏tab不被系统默认主题上色
        binding.navHome.itemIconTintList = null
        binding.navHome.itemRippleColor = null
        //初始化主页面三个fragment
        setupVP()
    }

    override fun onResume() {
        super.onResume()
        userVm.loadInfo()
    }


    override fun initEvent() {
        //点击打开侧边抽屉
        binding.openDrawer.setOnClickListener {
            binding.main.openDrawer(GravityCompat.START)
        }
        //点击侧边抽屉跳转
        binding.drawer.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId){

            }
            true
        }
        binding.search.setOnClickListener {
            ARouter.getInstance().build(RoutePath.SEEK).navigation()
        }

        // 整个 header 点击
        val headerView = binding.drawer.getHeaderView(0)
        headerView.setOnClickListener {
             ARouter.getInstance().build(RoutePath.LOGIN).navigation()
        }
        //更新抽屉页的头像昵称
        userVm.profile.observe(this@HomeActivity) { it ->
            val avatar = headerView.findViewById<ImageView>(R.id.avatar)
            val userName = headerView.findViewById<TextView>(R.id.user_name)
            if (it != null) {
                userName.text = it.nickname
                val avatarUrl = it.avatarUrl.replace("http://", "https://")
                val glideUrl = com.bumptech.glide.load.model.GlideUrl(
                    avatarUrl,
                    com.bumptech.glide.load.model.LazyHeaders.Builder()
                        .addHeader("Referer", "https://music.163.com/")
                        .build()
                )
                Glide.with(this@HomeActivity)
                    .load(glideUrl)
                    .placeholder(R.drawable.drawer_avatar)
                    .circleCrop()
                    .into(avatar)
            } else {
                userName.text = "游客登录"
            }
        }
    }

    fun setupVP(){
        val VpAdapter= HomeVPAdapter(this)
        VpAdapter.setFragments(
            listOf<Fragment>(
                TopFragment(),
                BlankFragment(),
                MyFragment()
            )
        )
        binding.vp2.adapter=VpAdapter
        binding.vp2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.navHome.selectedItemId=when(position){
                    0 ->R.id.nav_main
                    1 ->R.id.nav_discover
                    2->R.id.nav_my
                    else -> R.id.nav_main
                }
            }
        })
        binding.navHome.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_main -> binding.vp2.currentItem = 0
                R.id.nav_discover -> binding.vp2.currentItem = 1
                R.id.nav_my -> binding.vp2.currentItem = 2
            }
            true
        }
    }
    }
