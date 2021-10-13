package com.codeheros.movieapp.remote.model

import com.codeheros.movieapp.remote.model.MovieResult
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by sankar on 2021-10-11.
 */
data class MoviesListResult(
    @Expose
    @SerializedName("page")
    val page: Int? = 0,
    @Expose
    @SerializedName("total_pages")
    val totalPages: Int? = 0,
    @Expose
    @SerializedName("total_results")
    val totalResults: Int? = 0,
    @Expose
    @SerializedName("results")
    val results: List<MovieResult>? = emptyList()
)