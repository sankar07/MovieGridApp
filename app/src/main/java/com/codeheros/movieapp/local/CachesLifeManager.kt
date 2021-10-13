package com.codeheros.movieapp.local

import android.app.Application
import android.content.Context
import com.codeheros.movieapp.model.MovieListType
import java.util.Calendar
import java.util.TimeZone
import java.util.concurrent.TimeUnit

/**
 * Created by sankar on 2021-10-11.
 */
class CachesLifeManager constructor(app: Application) {
    companion object {
        private const val FILE_NAME = "caches"

        /* Caches data keys */
        private const val MOVIE_DETAILS_CACHE = "movie_"
        private const val MOVIES_LIST_UPCOMING_KEY = "movies_list_upcoming"

        /* Caches lifetime keys */
        private val MOVIE_DETAILS_LIFE_TIME = TimeUnit.MILLISECONDS.convert(15, TimeUnit.DAYS)
        private val MOVIES_LIST_UPCOMING_LIFE_TIME =
            TimeUnit.MILLISECONDS.convert(12, TimeUnit.HOURS)
    }

    private val preferences = app.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    private fun getCurrentDate() = Calendar.getInstance(TimeZone.getDefault()).timeInMillis

    private fun isCacheValid(key: String): Boolean {
        val currentDate = getCurrentDate()
        val lastFetch = preferences.getLong(key, currentDate)
        return lastFetch > currentDate
    }

    private fun generateCache(key: String = "", lifeTime: Long = 0L) = preferences.edit().apply {
        putLong(key, getCurrentDate() + lifeTime)
    }.apply()

    fun generateMovieDetailsCache(movieId: Int) {
        val movieKey = "$MOVIE_DETAILS_CACHE$movieId"
        generateCache(movieKey, MOVIE_DETAILS_LIFE_TIME)
    }

    fun movieCacheIsValid(movieId: Int) = "$MOVIE_DETAILS_CACHE$movieId".run {
        isCacheValid(this)
    }

    fun listCacheIsValid(type: MovieListType) = when (type) {
        MovieListType.MOVIE_LIST_UPCOMING -> isCacheValid(MOVIES_LIST_UPCOMING_KEY)
    }

    fun generateListCache(type: MovieListType) = when (type) {
        MovieListType.MOVIE_LIST_UPCOMING ->
            generateCache(MOVIES_LIST_UPCOMING_KEY, MOVIES_LIST_UPCOMING_LIFE_TIME)
    }
}