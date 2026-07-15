package com.example.lib.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/*
 * @Module     : com.example.lib.common
 * @FileName   : BaseFragment
 * @Author     : lsj
 * @CreateTime : 2026/7/15 16:00
 * @Desc       : 
 */
abstract class BaseFragment <VB: ViewBinding>: Fragment(){
    protected abstract fun getViewBinding():VB
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding()
        return _binding!!.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        initEvent()

    }
    abstract fun initEvent()
    abstract fun observeData()
    override fun onDestroyView() {
        super.onDestroyView()
        //保证binding变量的有效生命周期是在onCreateView()函数和onDestroyView()函数之间
        _binding = null//置空_binding
    }


}