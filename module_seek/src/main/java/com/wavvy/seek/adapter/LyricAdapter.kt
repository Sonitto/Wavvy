package com.wavvy.seek.adapter


import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.module_seek.databinding.ItemLyricBinding
import com.wavvy.seek.data.SongLyric

/*
 * @Module     : com.wavvy.seek.adapter
 * @FileName   : LyricAdapter
 * @Author     : lsj
 * @CreateTime : 2026/7/20 18:55
 * @Desc       : 
 */
class LyricAdapter: ListAdapter<SongLyric, LyricAdapter.ViewHolder>(object : DiffUtil.ItemCallback<SongLyric>(){
    override fun areItemsTheSame(
        oldItem: SongLyric,
        newItem: SongLyric
    ): Boolean {
       return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(
        oldItem: SongLyric,
        newItem: SongLyric
    ): Boolean {
        return oldItem==newItem
    }

}){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(binding: ItemLyricBinding): RecyclerView.ViewHolder(binding.root){

    }

}