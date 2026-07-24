package com.example.musicPlayer.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lib.common.BaseActivity
import com.example.module.musicplayer.R
import com.example.module.musicplayer.databinding.ActivityCommentBinding
import com.example.musicPlayer.util.CommentAdapter
import com.example.musicPlayer.util.CommentViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CommentActivity : BaseActivity<ActivityCommentBinding>() {

    private val vm:CommentViewModel by viewModels()
    private val adapter = CommentAdapter()
    override fun getViewBinding(): ActivityCommentBinding = ActivityCommentBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getLongExtra("songId", 0L)
        lifecycleScope.launch {
            vm.getComments(id).collectLatest { data ->
                adapter.submitData(data)
            }
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest {state->
                binding.loading.isVisible = true
                when (val refresh = state.refresh) {
                    is LoadState.Loading -> {
                        binding.loading.isVisible = true
                        binding.error.isVisible=false
                    }
                    is LoadState.Error -> {
                        binding.loading.isVisible = false
                        binding.error.isVisible=true
                        val error = refresh.error
                        Toast.makeText(this@CommentActivity, "加载失败：${error.message}", Toast.LENGTH_SHORT).show()
                    }
                   is LoadState.NotLoading ->{
                       binding.loading.isVisible =false
                       binding.error.isVisible=false
                   }
                }
            }
        }
    }

    override fun initEvent() {
        binding.rvComment.apply {
            adapter = this@CommentActivity.adapter
            layoutManager= LinearLayoutManager(context)
        }
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.error.setOnClickListener {
            adapter.retry()
        }
    }
}