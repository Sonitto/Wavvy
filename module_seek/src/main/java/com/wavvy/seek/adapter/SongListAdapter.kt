package com.wavvy.seek.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.module_seek.databinding.ItemSonglistBinding
import com.wavvy.seek.data.Playlists

/*
 * @Module     : com.wavvy.seek.adapter
 * @FileName   : SongListAdapter
 * @Author     : lsj
 * @CreateTime : 2026/7/20 19:06
 * @Desc       : 
 */
class SongListAdapter: ListAdapter<Playlists, SongListAdapter.ViewHolder>(object : DiffUtil.ItemCallback<Playlists>(){
    override fun areItemsTheSame(
        oldItem: Playlists,
        newItem: Playlists
    ): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Playlists,
        newItem: Playlists
    ): Boolean {
        return oldItem==newItem
    }

}) {
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

    inner class ViewHolder(binding: ItemSonglistBinding): RecyclerView.ViewHolder(binding.root){

    }
}