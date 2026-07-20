package com.example.module.home.activity.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * description : Home页面ViewPager2适配器
 * author : Cherry77551
 * date : 2026/7/18
 */
class HomeVPAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private val fragments = mutableListOf<Fragment>()

    fun setFragments(list: List<Fragment>) {
        fragments.clear()
        fragments.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}
