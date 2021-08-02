package com.sample.player.data.source.video

import com.sample.player.data.mapper.video.RemoteVideoListToEntityMapper
import com.sample.player.data.mapper.video.RemoteVideoModelToEntityMapper
import com.sample.player.data.remote.APIService
import com.sample.player.domain.model.base.SuccessResult
import com.sample.player.domain.model.base.UseCaseResult
import com.sample.player.domain.model.video.Video
import javax.inject.Inject

class VideoRemoteDataSourceImpl @Inject constructor(
    private val apiService: APIService,
    private val remoteVideoModelToEntityMapper: RemoteVideoModelToEntityMapper,
    private val remoteVideoListToEntityMapper: RemoteVideoListToEntityMapper
) : VideoRemoteDataSource {
    override suspend fun getVideos(): UseCaseResult<List<Video>> {
        return SuccessResult(remoteVideoListToEntityMapper.map(apiService.getVideos()))
    }

    override suspend fun getVideoDetails(videoId: Int): UseCaseResult<Video> {
        return SuccessResult(remoteVideoModelToEntityMapper.map(apiService.getVideoDetail(videoId)))
    }
}