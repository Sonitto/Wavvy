package com.wavvy.tophome.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module_tophome.databinding.ItemLovedSongBinding
import com.wavvy.tophome.data.LoveSong

/*
 * @Module     : com.wavvy.tophome
 * @FileName   : LoveSongAdapter
 * @Author     : lsj
 * @CreateTime : 2026/7/18 20:11
 * @Desc       : 网友都爱听的adapter
 */
class LoveSongAdapter(val context: Context): ListAdapter<LoveSong, LoveSongAdapter.ViewHolder>(object : DiffUtil.ItemCallback<LoveSong>(){
    override fun areItemsTheSame(
        oldItem: LoveSong,
        newItem: LoveSong
    ): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(
        oldItem: LoveSong,
        newItem: LoveSong
    ): Boolean {
       return oldItem==newItem
    }

}) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            binding= ItemLovedSongBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(binding: ItemLovedSongBinding): RecyclerView.ViewHolder(binding.root){
        private var currentData : LoveSong? = null
        private val ivLoveSong=binding.ivLovedImg
        private var tvName=binding.tvLoveName

        fun bind(data: LoveSong){
            currentData=data
            tvName.text=data.name
            Glide.with(context).load(data.picUrl).into(ivLoveSong)
        }
    }
}