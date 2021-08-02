package com.sample.player.domain.model.video

import com.sample.player.domain.model.base.Entity

data class Video(
    val id: Int,
    val title: String,
    val url: String
) : Entity
