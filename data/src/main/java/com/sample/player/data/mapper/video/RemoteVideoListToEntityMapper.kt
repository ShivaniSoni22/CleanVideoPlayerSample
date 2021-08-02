package com.sample.player.data.mapper.video

import com.sample.player.data.mapper.base.ModelListMapper
import com.sample.player.data.model.video.RemoteVideoModel
import com.sample.player.domain.model.video.Video
import javax.inject.Inject

class RemoteVideoListToEntityMapper @Inject constructor(mapper: RemoteVideoModelToEntityMapper) :
    ModelListMapper<RemoteVideoModel, Video>(mapper)