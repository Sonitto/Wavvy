package com.example.module.home.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.lib.common.BaseActivity
import com.example.lib.route.RoutePath
import com.example.module.home.activity.ViewModel.UserViewModel
import com.example.module.home.databinding.ActivityHomeBinding
import com.example.module.home.R
import com.bumptech.glide.Glide

@Route(path = RoutePath.HOME)
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    private val userVm: UserViewModel by viewModels()
    override fun getViewBinding(): ActivityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //强制使导航栏tab不被系统默认主题上色
        binding.navHome.itemIconTintList = null
        binding.navHome.itemRippleColor = null
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
        // 整个 header 点击
        val headerView = binding.drawer.getHeaderView(0)
        headerView.setOnClickListener {
             ARouter.getInstance().build(RoutePath.LOGIN).navigation()
        }
        //更新抽屉页的头像昵称
        userVm.profile.observe(this@HomeActivity){it ->
            val avatar = headerView.findViewById<ImageView>(R.id.avatar)
            val userName = headerView.findViewById<TextView>(R.id.user_name)
            if(it != null){
            userName.text = it.nickname
            Glide.with(this@HomeActivity)
                .load(it.avatarUrl)
                .placeholder(R.drawable.drawer_avatar)
                .into(avatar)
        }else{
            userName.text="游客登录"
            }
        }
    }
}
