package com.example.wavvy.lib.base

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.wavvy.R

abstract class BaseActivity<VB: ViewBinding>: AppCompatActivity(){
    protected val binding :VB by lazy{
        getViewBinding()
    }
    protected abstract fun getViewBinding():VB
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setImmersiveStatusBar()
        initEvent()

    }
    fun setImmersiveStatusBar() {}

    //注册点击事件
    abstract fun initEvent()

}