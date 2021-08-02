package com.sample.player.domain.usecase.video

import com.sample.player.domain.model.base.UseCaseResult
import com.sample.player.domain.model.video.Video
import com.sample.player.domain.repository.VideoRepository
import com.sample.player.domain.usecase.UseCase
import javax.inject.Inject

class GetVideosUseCase @Inject constructor(
    private val videoRepository: VideoRepository
) : UseCase<Unit, UseCaseResult<List<Video>>> {
    override suspend fun invoke(param: Unit?): UseCaseResult<List<Video>> {
        return videoRepository.getVideos()
    }
}