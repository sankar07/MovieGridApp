package com.codeheros.movieapp.domain

import com.codeheros.movieapp.repository.MovieDetailsRepository
import com.codeheros.movieapp.model.MovieDetails
import com.codeheros.movieapp.repository.MoviesListRepository
import javax.inject.Inject

/**
 * Created by sankar on 2021-10-13.
 */

class GetMovieDetailsUseCase @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository,
    private val moviesListRepository: MoviesListRepository
) {

    suspend fun getMovieDetails(movieId: Int) = interact {
        try {
            movieDetailsRepository.getMovieDetails(movieId)
        } catch (e: Exception) {
            moviesListRepository.getMovieListResult(movieId).run {
                MovieDetails(
                    id = id,
                    title = title,
                    posterURL = posterURL
                )
            }
        }
    }
}