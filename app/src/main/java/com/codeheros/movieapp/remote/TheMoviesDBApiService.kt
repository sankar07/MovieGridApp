package com.codeheros.movieapp.remote

import com.codeheros.movieapp.remote.model.MovieSummary
import com.codeheros.movieapp.remote.model.MoviesListResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interface containing all the functions that allow
 * access to **TheMovieDB** API in Retrofit definitions.
 *
 * Created by sankar on 2021-10-11.
 */
interface TheMoviesDBApiService {

    /**
     * Get a list of nowplaying in theatres.
     */
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page:Int): MoviesListResult
    /**
     * Retrieves the details of a given movie.
     * @param movieId Integer unique identifier for the movie.
     */
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): MovieSummary
}