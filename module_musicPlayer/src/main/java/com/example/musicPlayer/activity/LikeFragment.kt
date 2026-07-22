package com.example.musicPlayer.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lib.common.BaseFragment
import com.example.module.musicplayer.R
import com.example.module.musicplayer.databinding.FragmentLikeBinding
import com.example.musicPlayer.util.MusicService
import com.example.musicPlayer.util.SongAdapter
import com.example.musicPlayer.util.UidManager
import com.example.musicPlayer.vm.PlaylistViewModel


class LikeFragment : BaseFragment<FragmentLikeBinding>() {
    private val vm: PlaylistViewModel by activityViewModels()
    private lateinit var adapter: SongAdapter
    override fun getViewBinding(): FragmentLikeBinding= FragmentLikeBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uid=UidManager.getUid()
        if(uid != 0L){
            vm.loadLikeSongs(uid)
        }
    }

    override fun initEvent() {
        adapter= SongAdapter().apply {
            onItemClick={song ->
                MusicService.play(requireContext(),song)
            }
        }
        binding.likeRv.apply {
            layoutManager= LinearLayoutManager(context)
            adapter=this@LikeFragment.adapter
        }
    }

    override fun observeData() {
        vm.songs.observe(this){songs ->
            adapter.submitList(songs)
        }
    }
}