package com.zrcoding.android_challenge.di

import com.zrcoding.android_challenge.data.repositories.MovieRepository
import com.zrcoding.android_challenge.data.repositories.MovieRepositoryImpl
import dagger.Binds
import javax.inject.Singleton

abstract class RepositoryModule{
    @Binds
    @Singleton
    abstract fun providePostRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository
}