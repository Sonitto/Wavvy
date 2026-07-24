package com.wavvy.seek.adapter


import android.view.LayoutInflater
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
        return ViewHolder(binding = ItemLyricBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
       holder.bind(getItem(position))
    }

    inner class ViewHolder(binding: ItemLyricBinding): RecyclerView.ViewHolder(binding.root){
        private val tvLyric=binding.tvLyric
        private val tvLyricName=binding.tvLyricName
        private val tvLyricAuthor=binding.tvLyricAuthor
        fun bind(data: SongLyric){
            tvLyric.text=data.lyrics.txt
            tvLyricName.text=data.name
            tvLyricAuthor.text=data.getSinger()
        }

    }

}