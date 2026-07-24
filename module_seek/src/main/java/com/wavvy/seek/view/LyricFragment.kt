package com.wavvy.seek.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lib.common.BaseFragment
import com.example.module_seek.databinding.FragmentLyricBinding
import com.wavvy.seek.adapter.LyricAdapter
import com.wavvy.seek.viewmodel.SeekResultViewModel

class LyricFragment : BaseFragment<FragmentLyricBinding>(){
    override fun getViewBinding(): FragmentLyricBinding {
        return FragmentLyricBinding.inflate(layoutInflater)
    }
    private var keyword: String?=null
    private val viewModel  by lazy {
        ViewModelProvider(this)[SeekResultViewModel::class.java]
    }
    private val lyricsAdapter by lazy {
        LyricAdapter()
    }

    override fun initEvent() {
        binding.rvLyric.adapter=lyricsAdapter
        binding.rvLyric.layoutManager= LinearLayoutManager(requireContext())
        keyword?.let {
            viewModel.upSingleData(it)
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        keyword = arguments?.getString("keyword") ?: ""

    }


    override fun observeData() {
        viewModel.lyricData.observe(this@LyricFragment){
            lyricsAdapter.submitList( it.result.songs)
        }

    }
    companion object {
        fun newInstance(keyword: String?): LyricFragment {
            val fragment = LyricFragment()
            fragment.arguments = Bundle().apply {
                putString("keyword", keyword)
            }
            return fragment
        }
    }


}