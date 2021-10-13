package com.codeheros.movieapp.remote.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import com.codeheros.movieapp.model.Movie
import com.codeheros.movieapp.model.MovieDetails
import com.codeheros.movieapp.remote.TheMoviesDBApiAuthInterceptor
import com.codeheros.movieapp.remote.TheMoviesDBApiService
import com.codeheros.movieapp.remote.map.Mapper
import com.codeheros.movieapp.remote.map.MovieDetailsMapper
import com.codeheros.movieapp.remote.map.MovieResultMapper
import com.codeheros.movieapp.remote.model.MovieResult
import com.codeheros.movieapp.remote.model.MovieSummary
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by sankar on 2021-10-11.
 */
@Module
class RemoteModule(
    private val isDebug: Boolean,
    private val apiKey: String,
    private val baseURL: String,
    private val baseImageURL: String
) {
    private val authInterceptor by lazy { TheMoviesDBApiAuthInterceptor(apiKey) }

    private val gson: Gson by lazy { GsonBuilder().excludeFieldsWithoutExposeAnnotation().create() }

    private val gsonConverter: GsonConverterFactory by lazy { GsonConverterFactory.create(gson) }

    private val httpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            if (isDebug) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }
    }
    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authInterceptor)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(gsonConverter)
            .baseUrl(baseURL)
            .build()
    }

    @Provides
    @Singleton
    fun provideTheMoviesDBApiService(): TheMoviesDBApiService =
        retrofit.create(TheMoviesDBApiService::class.java)

    @Provides
    fun provideMovieResultMapper(): Mapper<MovieResult, Movie> = MovieResultMapper(baseImageURL)

    @Provides
    fun provideMovieDetailsMapper(): Mapper<MovieSummary, MovieDetails> = MovieDetailsMapper(baseImageURL)
}