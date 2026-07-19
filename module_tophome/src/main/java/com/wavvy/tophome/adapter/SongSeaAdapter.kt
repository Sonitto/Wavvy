package com.wavvy.tophome.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module_tophome.databinding.ItemSongseaBinding
import com.wavvy.tophome.data.DailySongList

/*
 * @Module     : com.wavvy.tophome
 * @FileName   : SongSeaAdapter
 * @Author     : lsj
 * @CreateTime : 2026/7/17 11:38
 * @Desc       :
 */
class SongSeaAdapter (private val context: Context):
    ListAdapter<List<DailySongList>, SongSeaAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<List<DailySongList>>(){
        override fun areItemsTheSame(
            oldItem: List<DailySongList>,
            newItem: List<DailySongList>
        ): Boolean {
            return oldItem.size==newItem.size
        }

        override fun areContentsTheSame(
            oldItem: List<DailySongList>,
            newItem: List<DailySongList>
        ): Boolean {
            if (oldItem.size != newItem.size) return false
            for (i in 0 until oldItem.size){
                if (oldItem[i] != newItem[i]) return false
            }
            return true
        }

    }) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            binding = ItemSongseaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(binding: ItemSongseaBinding): RecyclerView.ViewHolder(binding.root){
        private var currentData: List<DailySongList>?=null
        //用列表暂时储存下标题，作者名，图片，方便绑定
        private val titles by lazy {
            listOf(binding.tvName,binding.tvName2,binding.tvName3)
        }
        private val authors by lazy {
            listOf(binding.tvAuthorMusic,binding.tvAuthorMusic2,binding.tvAuthorMusic3)
        }
        private val items by lazy {
            listOf(binding.item1,binding.item2,binding.item3)
        }
        private val image by lazy {
            listOf(binding.ivItemPicture,binding.ivItemPicture2,binding.ivItemPicture3)
        }

        fun bind(data: List<DailySongList>){
            currentData=data
            data.forEachIndexed { index, list ->
                titles[index].text=list.songName
                authors[index].text=list.getSinger()
                Glide.with(context).load(list.album.coverImg).into(image[index])
            }

        }
    }
}