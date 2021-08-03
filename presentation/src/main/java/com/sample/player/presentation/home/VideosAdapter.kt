package com.sample.player.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.Player
import com.sample.player.databinding.ItemVideoBinding
import com.sample.player.domain.model.video.Video
import com.sample.player.presentation.util.PlayerStateCallback
import com.sample.player.presentation.util.PlayerViewAdapter.Companion.releaseRecycledPlayers

class VideosAdapter(val listener: (Video) -> Unit) :
    ListAdapter<Video, VideosAdapter.VideoViewHolder>(TagDU()), PlayerStateCallback {

//    private var mItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(
            ItemVideoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onViewRecycled(holder: VideosAdapter.VideoViewHolder) {
        val position = holder.adapterPosition
        releaseRecycledPlayers(position)
        super.onViewRecycled(holder)
    }
//
//    fun setOnItemClickListener(mItemClickListener: OnItemClickListener?) {
//        this.mItemClickListener = mItemClickListener
//    }
//
//    interface OnItemClickListener {
//        fun onItemClick(view: View, position: Int, video: Video)
//    }

    inner class VideoViewHolder(private val mBinding: ItemVideoBinding) : RecyclerView.ViewHolder(mBinding.root) {

        fun bind(video: Video) {
            ViewCompat.setTransitionName(mBinding.player, "playerView")
//            mBinding.root.setOnClickListener {
//                mItemClickListener!!.onItemClick(
//                    mBinding.player,
//                    adapterPosition,
//                    video
//                )
//                listener.invoke(video)
//            }
            mBinding.apply {
                txtTitle.text = video.title
                dataModel = video
                callback = this@VideosAdapter
                index = adapterPosition
                executePendingBindings()
            }
            itemView.setOnClickListener {
                listener.invoke(video)
            }
        }

    }

    private class TagDU : DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(oldItem: Video, newItem: Video) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Video, newItem: Video) =
            oldItem == newItem
    }

    companion object {
        const val TAG = "TagAdapter"
    }

    override fun onVideoDurationRetrieved(duration: Long, player: Player) {
    }

    override fun onVideoBuffering(player: Player) {
    }

    override fun onStartedPlaying(player: Player) {
    }

    override fun onFinishedPlaying(player: Player) {
    }

}