package com.example.module.login.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.lib.common.BaseActivity
import com.example.lib.route.RoutePath
import com.example.module.login.R
import com.example.module.login.databinding.ActivityLoginBinding
import com.example.module.login.vm.GuestViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Route(path= RoutePath.LOGIN)
class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    private val guestVm: GuestViewModel by viewModels()
    override fun getViewBinding(): ActivityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initEvent() {
        //跳转二维码登录
        binding.btnCode.setOnClickListener {
            startActivity(Intent(this, QrActivity::class.java))
        }
        //返回
        binding.btnBack.setOnClickListener {
            ARouter.getInstance().build(RoutePath.HOME).navigation()
        }
        //游客登录
        binding.btnGuest.setOnClickListener {
            guestVm.guestLogin()
        }
        guestVm.status.observe(this) { text ->
            Toast.makeText(this@LoginActivity, text, Toast.LENGTH_SHORT).show()
        }

        //判断是否登陆成功
        lifecycleScope.launch {
            guestVm.loginSuccess.collect { it ->
                if (it){
                    ARouter.getInstance().build(RoutePath.HOME).navigation()
                    finish()}
            }
        }
    }
}
