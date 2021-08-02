package com.sample.player.domain.repository

import com.sample.player.domain.model.base.UseCaseResult
import com.sample.player.domain.model.video.Video

interface VideoRepository {

    suspend fun getVideos(): UseCaseResult<List<Video>>

    suspend fun getVideoDetails(videoId : Int) : UseCaseResult<Video>
}