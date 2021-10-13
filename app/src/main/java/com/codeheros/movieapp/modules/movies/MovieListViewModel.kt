package com.codeheros.movieapp.modules.movies

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.codeheros.movieapp.domain.GetMovieListUseCase
import javax.inject.Inject

/**
 * Created by sankar on 2021-10-11.
 */
class MovieListViewModel @Inject constructor(private val getListUseCase: GetMovieListUseCase) : ViewModel() {


    val movies = Pager(PagingConfig(pageSize = 20,initialLoadSize = 20)) {
        getListUseCase
    }.flow
}