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

        }

    override fun initEvent() {

    }
}
