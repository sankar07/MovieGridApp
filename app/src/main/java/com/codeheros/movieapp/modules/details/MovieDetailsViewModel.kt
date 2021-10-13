package com.codeheros.movieapp.modules.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.launchInIOForLiveData
import com.codeheros.movieapp.domain.GetMovieDetailsUseCase
import javax.inject.Inject
/**
 * Created by sankar on 2021-10-13.
 */
class MovieDetailsViewModel @Inject constructor(private val movieDetailsUseCase: GetMovieDetailsUseCase) : ViewModel() {
    /**
     * Retrieves the details of a movie using its Id.
     */
    fun getMovieDetails(movieId: Int) = launchInIOForLiveData { movieDetailsUseCase.getMovieDetails(movieId) }
}