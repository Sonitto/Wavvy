package com.wavvy.seek.adapter


import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.module_seek.databinding.ItemSingleBinding
import com.wavvy.seek.data.Songs

/*
 * @Module     : com.wavvy.seek.adapter
 * @FileName   : SingleAdapter
 * @Author     : lsj
 * @CreateTime : 2026/7/20 13:29
 * @Desc       : 
 */
class SingleAdapter: ListAdapter<Songs, SingleAdapter.ViewHolder>(object : DiffUtil.ItemCallback<Songs>(){
    override fun areItemsTheSame(
        oldItem: Songs,
        newItem: Songs
    ): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Songs,
        newItem: Songs
    ): Boolean {
       return oldItem==newItem
    }

}) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SingleAdapter.ViewHolder {
        return ViewHolder(
            binding = ItemSingleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: SingleAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    inner class ViewHolder(binding: ItemSingleBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: Songs){

        }
    }
}