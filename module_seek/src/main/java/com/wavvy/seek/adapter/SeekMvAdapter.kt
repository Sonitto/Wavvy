package com.wavvy.seek.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module_seek.databinding.ItemMvBinding
import com.wavvy.seek.data.Mv

/*
 * @Module     : com.wavvy.seek.adapter
 * @FileName   : SeekMvAdapter
 * @Author     : lsj
 * @CreateTime : 2026/7/20 16:12
 * @Desc       : 
 */
class SeekMvAdapter(val context: Context): ListAdapter<Mv, SeekMvAdapter.ViewHolder>(object : DiffUtil.ItemCallback<Mv>(){
    override fun areItemsTheSame(
        oldItem: Mv,
        newItem: Mv
    ): Boolean {
       return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Mv,
        newItem: Mv
    ): Boolean {
        return oldItem==newItem
    }

}) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SeekMvAdapter.ViewHolder {
        return ViewHolder(
            binding = ItemMvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: SeekMvAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    inner class ViewHolder(binding: ItemMvBinding): RecyclerView.ViewHolder(binding.root){
        private var ivMv=binding.ivMv
        private var tvNum=binding.tvNum
        private var tvMvName=binding.tvMvName
        private var currentData : Mv?=null
        fun bind(data: Mv){
            tvNum.text=data.playCount.toString()
            currentData=data
            tvMvName.text=data.name
            Glide.with(context).load(data.cover).into(ivMv)
        }
    }
}