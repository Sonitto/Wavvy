package com.example.module.login.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.example.lib.common.BaseActivity
import com.example.lib.route.RoutePath
import com.example.module.login.R
import com.example.module.login.databinding.ActivityQrBinding
import com.example.module.login.vm.QrViewModel
import kotlinx.coroutines.launch


class QrActivity : BaseActivity<ActivityQrBinding>() {
    override fun getViewBinding(): ActivityQrBinding = ActivityQrBinding.inflate(layoutInflater)

    private val viewModel: QrViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun initEvent() {
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        //获取二维码
        lifecycleScope.launch {
            viewModel.qrBytes.collect { bytes ->
                bytes?.let {
                    Glide.with(this@QrActivity)
                        .asBitmap()
                        .load(it)
                        .into(binding.imQr)
                }
            }
        }
        //按钮上的文字
        lifecycleScope.launch {
            viewModel.status.collect { text ->
                binding.btnCheck.setText(text)
            }
        }
        //判断是否登陆成功
        lifecycleScope.launch {
            viewModel.loginSuccess.collect { it ->
                if (it){
                    ARouter.getInstance().build(RoutePath.HOME).navigation()
                    finish()}
            }
        }
        viewModel.startQr()
    }

}

