package com.sample.player.data.di

import com.sample.player.data.repository.VideoRepositoryImpl
import com.sample.player.domain.repository.VideoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindVideoRepository(repository: VideoRepositoryImpl): VideoRepository

}