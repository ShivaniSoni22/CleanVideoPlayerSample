package com.sample.player.data.model.video

import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json
import com.sample.player.data.model.base.RemoteModel

@JsonClass(generateAdapter = true)
data class RemoteVideoModel(
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "title")
    var title: String? = null,
    @Json(name = "url")
    var url: String? = null
) : RemoteModel
