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
import com.example.module_seek.databinding.FragmentSingerBinding
import com.wavvy.seek.adapter.SingerAdapter
import com.wavvy.seek.viewmodel.SeekResultViewModel

class SingerFragment : BaseFragment<FragmentSingerBinding>(){
    private val viewModel by lazy {
        ViewModelProvider(this)[SeekResultViewModel::class.java]
    }
    private val singerAdapter by lazy{
        SingerAdapter(requireContext())
    }
    private var keyword: String? = null
    override fun getViewBinding(): FragmentSingerBinding {
        return FragmentSingerBinding.inflate(layoutInflater)
    }

    override fun initEvent() {
       initView()
      keyword?.let {
          viewModel.upSingerData(it)
      }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        keyword = arguments?.getString("keyword") ?: ""


    }

    override fun observeData() {
        viewModel.singerData.observe(this@SingerFragment){
            singerAdapter.submitList(it.result.artists)
        }
    }
    fun initView(){
        binding.rvSeekSinger.adapter=singerAdapter
        binding.rvSeekSinger.layoutManager= LinearLayoutManager(requireContext())
    }
    //
    companion object{
        fun newInstance(keyword: String?):SingerFragment{
            val fragment= SingerFragment()
            fragment.arguments = Bundle().apply {
                putString("keyword", keyword)
            }
            return fragment
        }
    }

}