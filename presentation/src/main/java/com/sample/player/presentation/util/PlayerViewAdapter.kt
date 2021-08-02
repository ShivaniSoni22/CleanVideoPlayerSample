package com.sample.player.presentation.util

import android.net.Uri
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.ui.PlayerView

class PlayerViewAdapter {

    companion object {
        // for hold all players generated
        private var playersMap: MutableMap<Int, SimpleExoPlayer> = mutableMapOf()

        // for hold current player
        private var currentPlayingVideo: Pair<Int, SimpleExoPlayer>? = null

        fun releaseAllPlayers() {
            playersMap.map {
                it.value.release()
            }
        }

        // call when item recycled to improve performance
        fun releaseRecycledPlayers(index: Int) {
            playersMap[index]?.release()
        }

        // call when scroll to pause any playing player
        private fun pauseCurrentPlayingVideo() {
            if (currentPlayingVideo != null) {
                currentPlayingVideo?.second?.playWhenReady = false
            }
        }

        fun playIndexThenPausePreviousPlayer(index: Int) {
            if (playersMap[index]?.playWhenReady == false) {
                pauseCurrentPlayingVideo()
                playersMap[index]?.playWhenReady = true
                currentPlayingVideo = Pair(index, playersMap.get(index)!!)
            }
        }

        /*
        *  url is a url of stream video
        *  progressbar for show when start buffering stream
        * thumbnail for show before video start
        * */
        @JvmStatic
        @BindingAdapter(
            value = ["video_url", "on_state_change", "item_index", "autoPlay"],
            requireAll = false
        )
        fun PlayerView.loadVideo(
            url: String,
            callback: PlayerStateCallback,
            item_index: Int? = null,
            autoPlay: Boolean = false
        ) {
            val player = SimpleExoPlayer.Builder(context).build()
            player.repeatMode = Player.REPEAT_MODE_ALL
            // When changing track, retain the latest frame instead of showing a black screen
            setKeepContentOnPlayerReset(true)
            // We'll show the controller, change to true if want controllers as pause and start
            this.useController = false
            this.player = player
            // Provide url to load the video from here
            player.setMediaItem(
                MediaItem
                    .Builder()
                    .setUri(Uri.parse(url))
                    .build()
            )
            player.volume = 0f
            player.playWhenReady = autoPlay
            player.prepare()
            // add player with its index to map
            if (playersMap.containsKey(item_index))
                playersMap.remove(item_index)
            if (item_index != null)
                playersMap[item_index] = player
            player.play()
            this.player!!.addListener(object : Player.EventListener {
                override fun onPlayerError(error: ExoPlaybackException) {
                    super.onPlayerError(error)
                    Toast.makeText(
                        this@loadVideo.context, "Oops! Error occurred while playing media.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onPlaybackStateChanged(playbackState: Int) {
                    if (playbackState == Player.STATE_BUFFERING) {
                        callback.onVideoBuffering(player)
//                        thumbnail.visibility = View.VISIBLE
//                        progressbar.visibility = View.VISIBLE
                    }

                    if (playbackState == Player.STATE_READY) {
                        // [PlayerView] has fetched the video duration so this is the block to hide
                        // the buffering progress bar
//                        progressbar.visibility = View.GONE
//                        // set thumbnail gone
//                        thumbnail.visibility = View.GONE
                        callback.onVideoDurationRetrieved(this@loadVideo.player!!.duration, player)
                    }

                    if (playbackState == Player.STATE_READY && player.playWhenReady) {
                        // [PlayerView] has started playing/resumed the video
                        callback.onStartedPlaying(player)
                    }
                }

                override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {}
            })
        }
    }

}