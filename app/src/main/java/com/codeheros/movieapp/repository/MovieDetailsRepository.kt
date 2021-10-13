package com.codeheros.movieapp.repository

import com.codeheros.movieapp.local.CachesLifeManager
import com.codeheros.movieapp.local.dao.MovieDetailsDao
import com.codeheros.movieapp.model.MovieDetails
import com.codeheros.movieapp.remote.TheMoviesDBApiService
import com.codeheros.movieapp.remote.map.Mapper
import com.codeheros.movieapp.remote.model.MovieSummary
import javax.inject.Inject
/**
 * Created by sankar on 2021-10-13.
 */
class MovieDetailsRepository @Inject constructor(
    private val localDaoDataSource: MovieDetailsDao,
    private val apiDataSource: TheMoviesDBApiService,
    private val cachesLifeManager: CachesLifeManager,
    private val mapper: Mapper<MovieSummary, MovieDetails>
) {

    /**
     * Retrieves the movie summary from the internet and proceeds to cache the response.
     */
    private suspend fun getMovieDetailsRemote(movieId: Int): MovieDetails {
        val movie = mapper.map(apiDataSource.getMovieDetails(movieId))

        localDaoDataSource.createOrUpdate(movie)
        cachesLifeManager.generateMovieDetailsCache(movieId)

        return movie
    }

    /**
     * Retrieves the movie details from the local storage.
     */
    private suspend fun getMovieDetailsLocal(movieId: Int) = localDaoDataSource.read(movieId).first()

    /**
     * Retrieves the movie details from the best data source available.
     */
    suspend fun getMovieDetails(movieId: Int) = if (cachesLifeManager.movieCacheIsValid(movieId)) {
        getMovieDetailsLocal(movieId)
    } else {
        getMovieDetailsRemote(movieId)
    }
}