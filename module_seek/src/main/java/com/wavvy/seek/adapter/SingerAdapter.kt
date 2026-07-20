package com.wavvy.seek.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module_seek.databinding.ItemSingerBinding
import com.wavvy.seek.data.ArtistSinger

/*
 * @Module     : com.wavvy.seek.adapter
 * @FileName   : SingerAdapter
 * @Author     : lsj
 * @CreateTime : 2026/7/19 22:58
 * @Desc       : 
 */
class SingerAdapter(private val context: Context) : ListAdapter<ArtistSinger, SingerAdapter.ViewHolder>(object : DiffUtil.ItemCallback<ArtistSinger>(){
    override fun areItemsTheSame(
        oldItem: ArtistSinger,
        newItem: ArtistSinger
    ): Boolean {
       return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ArtistSinger,
        newItem: ArtistSinger
    ): Boolean {
       return oldItem==newItem
    }

}){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            binding = ItemSingerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(val binding: ItemSingerBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: ArtistSinger){
            binding.tvSingerName.text=data.name
            Glide.with(context).load(data.picUrl).circleCrop().into(binding.ivSinger)
        }
    }
}