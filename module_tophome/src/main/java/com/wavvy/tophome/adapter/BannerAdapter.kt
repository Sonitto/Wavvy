package com.wavvy.tophome.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module_tophome.databinding.ItemBannerBinding
import com.wavvy.tophome.data.BannerList

/*
 * @Module     : com.wavvy.tophome
 * @FileName   : BannerAdapter
 * @Author     : lsj
 * @CreateTime : 2026/7/16 22:28
 * @Desc       :
 */
class BannerAdapter(private val context: Context):
        ListAdapter<BannerList, BannerAdapter.ViewHolder>(object : DiffUtil.ItemCallback<BannerList>() {
            override fun areItemsTheSame(
                oldItem: BannerList,
                newItem: BannerList
            ): Boolean {
                return oldItem.targetId == newItem.targetId
            }

            override fun areContentsTheSame(
                oldItem: BannerList,
                newItem: BannerList
            ): Boolean {
                return oldItem == newItem
            }
        }
        ){

    var onItemClick:((BannerList)-> Unit)?=null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
      return ViewHolder(
          binding = ItemBannerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

//无限轮播
    override fun getItemCount(): Int =if (currentList.isEmpty())0 else Int.MAX_VALUE
   //获取准确位置
    override fun getItem(position: Int): BannerList {
        return super.getItem(position%currentList.size)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    inner class ViewHolder(binding: ItemBannerBinding): RecyclerView.ViewHolder(binding.root){
        private val imageBanner=binding.ivBanner
        private var currentData : BannerList?=null
        fun bind(data: BannerList){
            currentData=data
            Glide.with(context).load(data.pic).into(imageBanner)
        }
        init {
            imageBanner.setOnClickListener {
                val realPos =adapterPosition% currentList.size
                val item = getItem(realPos)
                onItemClick?.invoke(item)
            }
        }
    }

}