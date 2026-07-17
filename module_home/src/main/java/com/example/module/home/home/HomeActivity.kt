package com.example.module.home.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.lib.common.BaseActivity
import com.example.lib.route.RoutePath
import com.example.module.home.R
import com.example.module.home.databinding.ActivityHomeBinding

@Route(path = RoutePath.HOME)
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    override fun getViewBinding(): ActivityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //强制使导航栏tab不被系统默认主题上色
        binding.navHome.itemIconTintList = null
        binding.navHome.itemRippleColor = null

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
    }
}
