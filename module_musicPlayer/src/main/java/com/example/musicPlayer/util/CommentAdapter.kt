package com.example.musicPlayer.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module.musicplayer.R
import com.example.musicPlayer.data.Comment

class CommentAdapter : PagingDataAdapter<Comment, CommentAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Comment>() {
            override fun areItemsTheSame(old: Comment, new: Comment) = old.commentId == new.commentId
            override fun areContentsTheSame(old: Comment, new: Comment) = old == new
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val avatar: ImageView = view.findViewById(R.id.img_avatar)
        private val name: TextView = view.findViewById(R.id.tv_name)
        private val time: TextView = view.findViewById(R.id.tv_time)
        private val num: TextView = view.findViewById(R.id.tv_num)
        private val content: TextView = view.findViewById(R.id.tv_comment)

        fun bind(item: Comment?) {
            val c = item ?: return
            val user = c.user ?: return
            Glide.with(itemView).load(user.avatarUrl).circleCrop().into(avatar)
            name.text = user.nickname
            time.text = c.timeStr
            num.text = c.likedCount.toString()
            content.text = c.content
        }
    }
}
