package com.wavvy.seek.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lib.common.BaseFragment
import com.example.module_seek.databinding.FragmentMvBinding
import com.wavvy.seek.adapter.SeekMvAdapter
import com.wavvy.seek.viewmodel.SeekResultViewModel

class MvFragment : BaseFragment<FragmentMvBinding>(){
    private val viewModel by lazy {
        ViewModelProvider(this)[SeekResultViewModel::class.java]
    }
    private val mvAdapter by lazy{
        SeekMvAdapter(requireContext())
    }
    private var keyword: String? = null
    override fun getViewBinding(): FragmentMvBinding{
        return FragmentMvBinding.inflate(layoutInflater)
    }

    override fun initEvent() {
        initView()
        keyword?.let {
            viewModel.upMvData(it)
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        keyword = arguments?.getString("keyword") ?: ""


    }

    override fun observeData() {
        viewModel.mvData.observe(this@MvFragment){
            mvAdapter.submitList(it.result)
        }
    }
    fun initView(){
        binding.rvMv.adapter=mvAdapter
        binding.rvMv.layoutManager= LinearLayoutManager(requireContext())
    }
    //
    companion object{
        fun newInstance(keyword: String?): MvFragment{
            val fragment= MvFragment()
            fragment.arguments = Bundle().apply {
                putString("keyword", keyword)
            }
            return fragment
        }
    }

}
