package com.example.module.home.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lib.common.BaseActivity
import com.example.lib.common.BaseFragment
import com.example.module.home.R
import com.example.module.home.databinding.FragmentMyBinding


class MyFragment : BaseFragment<FragmentMyBinding>() {
    override fun getViewBinding(): FragmentMyBinding = FragmentMyBinding.inflate(layoutInflater)

    override fun initEvent() {

    }

    override fun observeData() {

    }


}


