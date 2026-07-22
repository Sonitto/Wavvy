package com.example.module.home.activity

import androidx.fragment.app.activityViewModels
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.example.lib.common.BaseFragment
import com.example.lib.route.RoutePath
import com.example.module.home.R
import com.example.module.home.activity.ViewModel.UserViewModel
import com.example.module.home.databinding.FragmentMyBinding


class MyFragment : BaseFragment<FragmentMyBinding>() {
    // 共享 Activity 作用域的 UserViewModel，这样 HomeActivity 登录后加载的数据能同步到 "我的" 页面
    private val userVm: UserViewModel by activityViewModels()
    override fun getViewBinding(): FragmentMyBinding = FragmentMyBinding.inflate(layoutInflater)

    override fun initEvent() {
        userVm.profile.observe(viewLifecycleOwner) { profile ->
            if (profile != null) {
                binding.tvUsername.text = profile.nickname
                val avatarUrl = profile.avatarUrl.replace("http://", "https://")
                //伪造一个头像图片来源
                val glideUrl = com.bumptech.glide.load.model.GlideUrl(
                    avatarUrl,
                    com.bumptech.glide.load.model.LazyHeaders.Builder()
                        .addHeader("Referer", "https://music.163.com/")
                        .build()
                )
                Glide.with(this@MyFragment)
                    .load(glideUrl)
                    .placeholder(R.drawable.drawer_avatar)
                    .circleCrop()
                    .into(binding.imvAvatar)
            } else {
                binding.tvUsername.text = "游客用户"
                binding.imvAvatar.setImageResource(R.drawable.drawer_avatar)
            }
        }
        //
        binding.myLike.setOnClickListener {
            ARouter.getInstance().build(RoutePath.PLAYLIST).withString("type","like").navigation()
        }
        binding.myHistory.setOnClickListener {
            ARouter.getInstance().build(RoutePath.PLAYLIST).withString("type","history").navigation()
        }
        }


    override fun observeData() {

    }


}

