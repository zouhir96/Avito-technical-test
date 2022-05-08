package com.zrcoding.android_challenge.di

import com.zrcoding.android_challenge.data.repositories.MovieRepository
import com.zrcoding.android_challenge.data.repositories.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{
    @Binds
    @Singleton
    abstract fun providePostRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository
}