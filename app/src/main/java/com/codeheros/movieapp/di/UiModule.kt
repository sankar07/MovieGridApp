package com.codeheros.movieapp.di

import com.codeheros.movieapp.modules.details.MovieDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.codeheros.movieapp.NavHostActivity
import com.codeheros.movieapp.modules.movies.MovieListFragment

@Module
abstract class UiModule {
    @ContributesAndroidInjector
    abstract fun contributeNavHostActivity(): NavHostActivity

    @ContributesAndroidInjector
    abstract fun contributeMovieListFragment(): MovieListFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailsFragment(): MovieDetailsFragment
}