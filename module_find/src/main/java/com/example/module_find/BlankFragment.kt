package com.example.module_find

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.lib.common.BaseFragment
import com.example.lib.route.RoutePath
import com.example.module_find.databinding.FragmentBlankBinding


@Route(path = RoutePath.FRAG_FIND)
class BlankFragment : BaseFragment<FragmentBlankBinding>() {
    override fun getViewBinding(): FragmentBlankBinding {
        return FragmentBlankBinding.inflate(layoutInflater)
    }
    private val viewModel by lazy{
        ViewModelProvider(this)[RankViewModel::class.java]
    }

    override fun initEvent() {
        binding.rvRank.adapter=rankAdapter
        binding.rvRank.layoutManager= GridLayoutManager(requireContext(),2)
        viewModel.upRankData()
    }
    private val rankAdapter by lazy {
        RankAdapter(requireContext())
    }


    override fun observeData() {
        viewModel.rankData.observe(this@BlankFragment){
            rankAdapter.submitList(it.list)
        }
    }


}