package com.sample.player.data.remote

import com.sample.player.data.model.video.RemoteVideoModel
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    @GET("contents")
    suspend fun getVideos(): List<RemoteVideoModel>

    @GET("contents/{videoId}")
    suspend fun getVideoDetail(@Path("videoId") videoId: Int): RemoteVideoModel
}