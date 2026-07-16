package com.example.module.home.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lib.common.BaseActivity
import com.example.lib.route.RoutePath
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

    }
}
