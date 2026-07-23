package com.wavvy.seek.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lib.common.BaseFragment
import com.example.module_seek.R
import com.example.module_seek.databinding.FragmentSingleBinding
import com.wavvy.seek.adapter.SingleAdapter
import com.wavvy.seek.viewmodel.SeekResultViewModel
class SingleFragment : BaseFragment<FragmentSingleBinding>(){
    override fun getViewBinding(): FragmentSingleBinding {
        return FragmentSingleBinding.inflate(layoutInflater)
    }
    private var keyword: String?=null
    private val viewModel  by lazy {
        ViewModelProvider(this)[SeekResultViewModel::class.java]
    }
    private val singleAdapter by lazy {
        SingleAdapter()
    }

    override fun initEvent() {
        binding.rvSingle.adapter=singleAdapter
        binding.rvSingle.layoutManager= LinearLayoutManager(requireContext())
        keyword?.let {
            viewModel.upSingleData(it)
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        keyword = arguments?.getString("keyword") ?: ""

    }


    override fun observeData() {
       viewModel.singleData.observe(this@SingleFragment){
           singleAdapter.submitList( it.result.songs)
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