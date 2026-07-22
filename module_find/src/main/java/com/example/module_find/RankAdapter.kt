package com.example.module_find


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module_find.data.DetailRank
import com.example.module_find.data.RankData
import com.example.module_find.databinding.ItemRankBinding

/*
 * @Module     : com.example.module_find
 * @FileName   : RankAdapter
 * @Author     : lsj
 * @CreateTime : 2026/7/20 22:59
 * @Desc       : 
 */
class RankAdapter(private val context: Context): ListAdapter<DetailRank, RankAdapter.ViewHolder>(object : DiffUtil.ItemCallback<DetailRank>(){
    override fun areItemsTheSame(
        oldItem: DetailRank,
        newItem: DetailRank
    ): Boolean {
       return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(
        oldItem: DetailRank,
        newItem: DetailRank
    ): Boolean {
        return oldItem==newItem
    }

}) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RankAdapter.ViewHolder {
     return ViewHolder(binding = ItemRankBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RankAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))

    }
    inner class ViewHolder(val binding: ItemRankBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: DetailRank){
            Glide.with(context).load(data.coverImgUrl).into(binding.ivRanks)
        }
    }
}