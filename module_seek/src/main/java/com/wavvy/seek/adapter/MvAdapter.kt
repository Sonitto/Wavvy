package com.wavvy.seek.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.module_seek.databinding.ItemMvBinding
import com.wavvy.seek.data.Mv

/*
 * @Module     : com.wavvy.seek.adapter
 * @FileName   : MvAdapter
 * @Author     : lsj
 * @CreateTime : 2026/7/20 16:12
 * @Desc       : 
 */
class MvAdapter(val context: Context): ListAdapter<Mv, MvAdapter.ViewHolder>(object : DiffUtil.ItemCallback<Mv>(){
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
    ): MvAdapter.ViewHolder {
        return ViewHolder(
            binding = ItemMvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: MvAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    inner class ViewHolder(binding: ItemMvBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: Mv){

        }
    }
}