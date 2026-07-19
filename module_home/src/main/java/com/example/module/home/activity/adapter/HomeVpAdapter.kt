package com.example.module.home.activity.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

/*
 * @Module     : com.example.module.home.activity.adapter
 * @FileName   : HomeVpAdapter
 * @Author     : lsj
 * @CreateTime : 2026/7/19 11:49
 * @Desc       : 
 */
class HomeVpAdapter(fragmentActivity: FragmentActivity,private val fragmentList: List<Fragment>): FragmentStateAdapter(fragmentActivity){
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

}