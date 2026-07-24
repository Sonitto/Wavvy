package com.wavvy.seek.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.module_seek.databinding.ItemRankListBinding
import com.wavvy.seek.data.Tracks

/*
 * @Module     : com.wavvy.seek
 * @FileName   : MusicRankAdapter
 * @Author     : lsj
 * @CreateTime : 2026/7/18 15:16
 * @Desc       : 搜索页的榜单rvadapter
 */
class MusicRankListAdapter (): ListAdapter<Tracks, MusicRankListAdapter.ViewHolder>(object: DiffUtil.ItemCallback<Tracks>(){
    override fun areItemsTheSame(
        oldItem: Tracks,
        newItem: Tracks
    ): Boolean {
      return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Tracks,
        newItem: Tracks
    ): Boolean {
       return oldItem==newItem
    }

}){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(binding = ItemRankListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
       holder.bind(getItem(position),position)
    }

    inner class ViewHolder(val binding: ItemRankListBinding ): RecyclerView.ViewHolder(binding.root){
        private var currentData : Tracks? = null
        fun bind(item: Tracks, position: Int){
            currentData=item
            binding.tvSongNum.text=(position+1).toString()
            binding.tvHotMusicName.text=item.name
        }
    }
}