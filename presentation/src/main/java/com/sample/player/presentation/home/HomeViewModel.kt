package com.sample.player.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sample.player.domain.model.base.doOnSuccess
import com.sample.player.domain.model.video.Video
import com.sample.player.domain.usecase.video.GetVideosUseCase
import com.sample.player.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getVideosUseCase: GetVideosUseCase
) : BaseViewModel() {

    private val _videos = MutableLiveData<List<Video>>()
    val videos: LiveData<List<Video>>
        get() = _videos


    fun getVideos() {
        viewModelScope.launch {
            getVideosUseCase.invoke()
                .doOnSuccess { result -> _videos.value = result }
        }
    }

}