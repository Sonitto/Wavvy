package com.wavvy.seek.adapter



import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.module_seek.databinding.ItemHotRankBinding
import com.wavvy.seek.data.Data
import com.wavvy.seek.data.Playlist
import com.wavvy.seek.data.Tracks

/*
 * @Module     : com.wavvy.seek
 * @FileName   : MusicRankAdapter
 * @Author     : lsj
 * @CreateTime : 2026/7/18 16:22
 * @Desc       : 榜单vp2的adapter
 */
class MusicRankAdapter(private val context: Context): ListAdapter<Playlist, MusicRankAdapter.ViewHolder>(object : DiffUtil.ItemCallback<Playlist>(){
    override fun areItemsTheSame(
        oldItem: Playlist,
        newItem: Playlist
    ): Boolean {
       return oldItem.name==newItem.name
    }

    override fun areContentsTheSame(
        oldItem: Playlist,
        newItem: Playlist
    ): Boolean {
        return oldItem==newItem
    }

} ){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MusicRankAdapter.ViewHolder {
        return ViewHolder(
            binding = ItemHotRankBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: MusicRankAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    inner class ViewHolder(binding: ItemHotRankBinding): RecyclerView.ViewHolder(binding.root){
        private var currentData: Playlist?=null
        private var rv: RecyclerView=binding.rvRank
        private var tvRankName=binding.tvHotRankName
        fun bind(data: Playlist){
            currentData=data
            tvRankName.text=data.name
            val limitedTracks = data.tracks.take(20)
            var adapter= MusicRankListAdapter()
            rv.adapter=adapter
            rv.layoutManager=
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter.submitList(limitedTracks)

        }
    }
}