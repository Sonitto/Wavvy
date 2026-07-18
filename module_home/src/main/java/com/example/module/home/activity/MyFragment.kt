package com.example.module.home.activity

import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.lib.common.BaseFragment
import com.example.module.home.R
import com.example.module.home.activity.ViewModel.UserViewModel
import com.example.module.home.databinding.FragmentMyBinding
import kotlin.getValue


class MyFragment : BaseFragment<FragmentMyBinding>() {
    private val userVm: UserViewModel by viewModels()
    override fun getViewBinding(): FragmentMyBinding = FragmentMyBinding.inflate(layoutInflater)

    override fun initEvent() {
        userVm.profile.observe(this@MyFragment){it ->
            binding.tvUsername.text = it.nickname?:"游客用户"
            Glide.with(this@MyFragment)
                .load(it.avatarUrl)
                .into(binding.imvAvatar)
        }
    }

    override fun observeData() {

    }


}


