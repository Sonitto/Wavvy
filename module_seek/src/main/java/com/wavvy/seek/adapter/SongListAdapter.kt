package com.wavvy.seek.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module_seek.databinding.ItemSonglistBinding
import com.wavvy.seek.data.Playlists

/*
 * @Module     : com.wavvy.seek.adapter
 * @FileName   : SongListAdapter
 * @Author     : lsj
 * @CreateTime : 2026/7/20 19:06
 * @Desc       : 
 */
class SongListAdapter(private val context: Context): ListAdapter<Playlists, SongListAdapter.ViewHolder>(object : DiffUtil.ItemCallback<Playlists>(){
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
        return ViewHolder(binding = ItemSonglistBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(binding: ItemSonglistBinding): RecyclerView.ViewHolder(binding.root){
        val tvSongName=binding.tvSongName
        val ivSongImg=binding.ivListImg
        fun bind(data: Playlists){
            tvSongName.text=data.name
            Glide.with(context).load(data.coverImgUrl).into(ivSongImg)
        }
    }
}