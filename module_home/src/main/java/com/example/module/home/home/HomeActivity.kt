package com.example.module.home.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lib.common.BaseActivity
import com.example.module.home.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    override fun getViewBinding(): ActivityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }

    override fun initEvent() {

    }
}
