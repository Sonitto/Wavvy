package com.wavvy.seek.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wavvy.seek.data.Playlist
import com.wavvy.seek.view.LyricFragment
import com.wavvy.seek.view.MvFragment
import com.wavvy.seek.view.SingerFragment
import com.wavvy.seek.view.SingleFragment
import com.wavvy.seek.view.SongListFragment

/*
 * @Module     : com.wavvy.seek.adapter
 * @FileName   : SeekResultVp2Adapter
 * @Author     : lsj
 * @CreateTime : 2026/7/23 14:46
 * @Desc       : 
 */
class SeekResultVp2Adapter (activity: FragmentActivity,private val keyword: String?) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 5
//滑倒对应下标创建相应fragment
    override fun createFragment(position: Int): Fragment{
        return when(position) {
            0-> LyricFragment.newInstance(keyword)
          1-> SingleFragment.newInstance(keyword)
          2-> SingerFragment.newInstance(keyword)
          3-> MvFragment.newInstance(keyword)
          4-> SongListFragment.newInstance(keyword)
            else -> throw IllegalArgumentException("无效下标：$position")
        }
    }
}
