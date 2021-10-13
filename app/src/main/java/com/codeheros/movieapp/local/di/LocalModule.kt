package com.codeheros.movieapp.local.di

import android.app.Application
import dagger.Module
import dagger.Provides
import com.codeheros.movieapp.local.AppDatabase
import com.codeheros.movieapp.local.CachesLifeManager

/**
 * Created by sankar on 2021-10-11.
 * Copyright © 2019 GROW. All rights reserved.
 */
@Module
class LocalModule(private val app: Application) {
    @Provides
    fun provideMovieDao() = AppDatabase.getInstance(app).provideMovieDao()

    @Provides
    fun provideMovieDetailsDao() = AppDatabase.getInstance(app).provideMovieDetailsDao()

    @Provides
    fun provideCacheManager() = CachesLifeManager(app)
}