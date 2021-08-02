package com.sample.player.data.di

import com.sample.player.data.source.video.VideoRemoteDataSource
import com.sample.player.data.source.video.VideoRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindVideoRemoteDataSource(dataSource: VideoRemoteDataSourceImpl): VideoRemoteDataSource
}