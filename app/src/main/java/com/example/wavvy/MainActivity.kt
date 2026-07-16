package com.example.wavvy

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.lib.common.BaseActivity
import com.example.lib.route.RoutePath
import com.example.wavvy.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Route(path = RoutePath.MAIN)
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

    }

    override fun initEvent() {
        lifecycleScope.launch {
            delay(2000)
            ARouter.getInstance().build(RoutePath.HOME).navigation()
            finish()
        }
    }
}