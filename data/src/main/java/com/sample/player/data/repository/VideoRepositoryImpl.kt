package com.sample.player.data.repository

import com.sample.player.data.source.video.VideoRemoteDataSource
import com.sample.player.domain.model.base.UseCaseResult
import com.sample.player.domain.model.video.Video
import com.sample.player.domain.repository.VideoRepository
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(private val videoRemoteDataSource: VideoRemoteDataSource) :
    VideoRepository, RepositoryCoroutineFriendly() {

    override suspend fun getVideos(): UseCaseResult<List<Video>> {
        return runCommand {
            videoRemoteDataSource.getVideos()
        }
    }

    override suspend fun getVideoDetails(videoId: Int): UseCaseResult<Video> {
        return runCommand {
            videoRemoteDataSource.getVideoDetails(videoId)
        }
    }
}