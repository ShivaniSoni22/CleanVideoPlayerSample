package com.sample.player.presentation.detail

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.util.NonNullApi
import com.google.android.exoplayer2.util.Util
import com.player.androidcore.ext.*
import com.sample.player.databinding.FragmentDetailBinding
import com.sample.player.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<DetailViewModel, FragmentDetailBinding>() {

    private lateinit var playbackStateListener: Player.EventListener
    private lateinit var playerState: String
    val STATE_RESUME_WINDOW = "resumeWindow"
    val STATE_RESUME_POSITION = "resumePosition"
    val STATE_PLAYER_FULLSCREEN = "playerFullscreen"
    val STATE_PLAYER_PLAYING = "playerOnPlay"

    private var videoUrl: String = ""
    override val viewModel: DetailViewModel by viewModels()

    val args: DetailFragmentArgs by navArgs()
    private var localPlayer: SimpleExoPlayer? = null
    private var currentWindow: Int = 0
    private var playbackPosition: Long = 0
    private var isFullscreen = false
    private var isPlayerPlaying = true

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(STATE_RESUME_WINDOW, localPlayer!!.currentWindowIndex)
        outState.putLong(STATE_RESUME_POSITION, localPlayer!!.currentPosition)
        outState.putBoolean(STATE_PLAYER_FULLSCREEN, isFullscreen)
        outState.putBoolean(STATE_PLAYER_PLAYING, isPlayerPlaying)
        super.onSaveInstanceState(outState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //        ViewCompat.setTransitionName(binding?.player!!, "PlayerView" )
//        sharedElementEnterTransition = TransitionInflater.from(requireContext())
//            .inflateTransition(R.transition.shared_image)
        if (savedInstanceState != null) {
            currentWindow = savedInstanceState.getInt(STATE_RESUME_WINDOW)
            playbackPosition = savedInstanceState.getLong(STATE_RESUME_POSITION)
            isFullscreen = savedInstanceState.getBoolean(STATE_PLAYER_FULLSCREEN)
            isPlayerPlaying = savedInstanceState.getBoolean(STATE_PLAYER_PLAYING)
        }
        viewModel.apply {
            getVideoDetails(args.videoId)

            videoDetail.observe(viewLifecycleOwner, { detail ->
                binding?.tvTitle?.text = detail.title
                videoUrl = detail.url
                initPlayer()
            })
        }

        binding?.player?.findViewById<ImageButton>(com.google.android.exoplayer2.R.id.exo_fullscreen)
            ?.setOnClickListener {
                handleFullScreenClicked()
            }
        playbackStateListener = object : Player.EventListener {

            override fun onPlaybackStateChanged(state: Int) {
                playerState = when (state) {
                    Player.STATE_BUFFERING -> {
                        "Exoplayer.STATE_BUFFERING"
                    }
                    Player.STATE_READY -> {
                        "Exoplayer.STATE_READY"
                    }
                    Player.STATE_IDLE -> {
                        "Exoplayer.STATE_IDLE"
                    }
                    Player.STATE_ENDED -> {
                        "Exoplayer.STATE_ENDED"
                    }
                    else -> "unknown state"
                }
            }

            override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {}

            @NonNullApi
            override fun onPlayerError(error: ExoPlaybackException) {
            }
        }
        localPlayer?.addListener(playbackStateListener)
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24) {
            initPlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        hideSystemUI()
        if (Util.SDK_INT < 24 || localPlayer == null) {
            initPlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT < 24) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        localPlayer?.apply {
            playWhenReady = false
            playbackPosition = currentPosition
            currentWindow = currentWindowIndex
            localPlayer?.removeListener(playbackStateListener)
            release()
        }
    }

    private fun initPlayer() {
        context ?: return
        SimpleExoPlayer.Builder(requireContext()).build().also {
            localPlayer = it
            binding?.player?.player = localPlayer
            binding?.player?.keepScreenOn = true

            localPlayer?.setMediaItem(
                MediaItem
                    .Builder()
                    .setUri(videoUrl)
                    .build()
            )
            localPlayer?.playWhenReady = true
            localPlayer?.seekTo(currentWindow, playbackPosition)
            localPlayer?.prepare()
            localPlayer?.play()
        }
    }

    private fun handleFullScreenClicked() {
        if (isPortrait()) {
            hideSystemUI()
            rotateScreenToLandscape()

            binding?.player?.let { player ->
                player.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                player.makeHeightAndWidthMatchParent()
            }
        } else {
            showSystemUI()
            rotateScreenToPortrait()

            binding?.player?.let { player ->
                player.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                player.setHeightDP(200)
            }
        }
    }

}