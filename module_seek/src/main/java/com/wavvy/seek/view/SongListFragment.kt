package com.wavvy.seek.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lib.common.BaseFragment
import com.example.module_seek.databinding.FragmentSongListBinding
import com.wavvy.seek.adapter.SongListAdapter
import com.wavvy.seek.viewmodel.SeekResultViewModel

class SongListFragment : BaseFragment<FragmentSongListBinding>(){
    override fun getViewBinding(): FragmentSongListBinding {
        return FragmentSongListBinding.inflate(layoutInflater)
    }
    private var keyword: String?=null
    private val viewModel  by lazy {
        ViewModelProvider(this)[SeekResultViewModel::class.java]
    }
    private val songListAdapter by lazy {
        SongListAdapter(requireContext())
    }

    override fun initEvent() {
        binding.rvSongList.adapter=songListAdapter
        binding.rvSongList.layoutManager= LinearLayoutManager(requireContext())
        keyword?.let {
            viewModel.upSingleData(it)
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        keyword = arguments?.getString("keyword") ?: ""

    }


    override fun observeData() {
        viewModel.songData.observe(this@SongListFragment){
            songListAdapter.submitList( it.result.playlists)
        }

    }
    //储存搜索词，防止页面重建参数丢失
    companion object {
        fun newInstance(keyword: String?): SingleFragment {
            val fragment = SingleFragment()
            fragment.arguments = Bundle().apply {
                putString("keyword", keyword)
            }
            return fragment
        }
    }


}