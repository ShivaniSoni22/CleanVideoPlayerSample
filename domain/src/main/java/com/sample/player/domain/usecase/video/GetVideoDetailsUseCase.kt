package com.sample.player.domain.usecase.video

import com.sample.player.domain.model.base.UseCaseResult
import com.sample.player.domain.model.video.Video
import com.sample.player.domain.repository.VideoRepository
import com.sample.player.domain.usecase.UseCase
import javax.inject.Inject

class GetVideoDetailsUseCase @Inject constructor(
    private val videoRepository: VideoRepository
) : UseCase<Int, UseCaseResult<Video>> {
    override suspend fun invoke(param: Int?): UseCaseResult<Video> {
        return videoRepository.getVideoDetails(param ?: 0)
    }
}