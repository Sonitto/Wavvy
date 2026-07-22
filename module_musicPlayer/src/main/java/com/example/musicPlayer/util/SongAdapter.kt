package com.example.musicPlayer.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module.musicplayer.R
import com.example.musicPlayer.data.Song

/**
 * description :
 * author : Cherry77551
 * date : 2026/7/22 13:37
 */
class SongAdapter : ListAdapter<Song, SongAdapter.ViewHolder>(diff()){
    var onItemClick:((Song) -> (Unit))?=null

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val ivCover: ImageView = view.findViewById(R.id.iv_cover)
        private val tvName: TextView = view.findViewById(R.id.tv_name)
        private val tvArtist: TextView = view.findViewById(R.id.tv_artist)
        private val tvDuration: TextView = view.findViewById(R.id.tv_duration)

        fun bind(song: Song) {
            Glide.with(itemView).load(song.coverUrl()).into(ivCover)
            tvName.text = song.name
            tvArtist.text = song.artistNames()
            tvDuration.text = song.duration()
            itemView.setOnClickListener { onItemClick?.invoke(song) }
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SongAdapter.ViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.item_song,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))

    }
    class diff: DiffUtil.ItemCallback<Song>(){
        override fun areItemsTheSame(
            oldItem: Song,
            newItem: Song
        ): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Song,
            newItem: Song
        ): Boolean {
            return oldItem==newItem
        }

    }
}