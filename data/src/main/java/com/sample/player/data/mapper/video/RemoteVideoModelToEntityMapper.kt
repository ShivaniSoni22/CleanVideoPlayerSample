package com.sample.player.data.mapper.video

import com.sample.player.data.mapper.base.ModelMapper
import com.sample.player.data.model.video.RemoteVideoModel
import com.sample.player.domain.model.video.Video
import javax.inject.Inject

class RemoteVideoModelToEntityMapper @Inject constructor() : ModelMapper<RemoteVideoModel, Video> {

    override fun map(input: RemoteVideoModel): Video {
        return Video(
            input.id ?: -1,
            input.title ?: "",
            input.url ?: ""
        )
    }
}