package com.sample.player.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.player.R
import com.sample.player.databinding.FragmentHomeBinding
import com.sample.player.domain.model.video.Video
import com.sample.player.presentation.base.BaseFragment
import com.sample.player.presentation.util.PlayerViewAdapter.Companion.playIndexThenPausePreviousPlayer
import com.sample.player.presentation.util.RecyclerViewScrollListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(){

//    private var playerView: View? = null
    override val viewModel: HomeViewModel by viewModels()
    private val TAG = "HomeFragment"

    // to handle scroll and get first visible item index
    private lateinit var scrollListener: RecyclerViewScrollListener

    private val videosAdapter: VideosAdapter by lazy {
        VideosAdapter { video ->
//            val extras = FragmentNavigatorExtras(playerView!! to "playerView")
//                        val extras = bundleOf("transition" to FragmentNavigatorExtras(playerView!! to "playerView"),
//            "videoId" to video.id)
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                    video.id
                )
//                ,
//                extras
//            R.id.action_homeFragment_to_detailFragment, extras
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        postponeEnterTransition()

        binding?.apply {
            rvVideos.layoutManager = LinearLayoutManager(context)
            rvVideos.adapter = videosAdapter
        }

        viewModel.apply {
            getVideos()
            videos.observe(viewLifecycleOwner, { list ->
                videosAdapter.submitList(list)
//                (view.parent as? ViewGroup)?.doOnPreDraw {
//                    startPostponedEnterTransition()
//                }
            })
        }

        scrollListener = object : RecyclerViewScrollListener() {
            override fun onItemIsFirstVisibleItem(index: Int) {
                Log.d("visible item index", index.toString())
                // play just visible item
                if (index != -1)
                    playIndexThenPausePreviousPlayer(index)
            }
        }

        binding?.rvVideos?.addOnScrollListener(scrollListener)
//        videosAdapter.setOnItemClickListener(object: VideosAdapter.OnItemClickListener {
//            override fun onItemClick(view: View, position: Int, video: Video) {
//                playerView = view
//
//            }
//
//        })
    }

}
