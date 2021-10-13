package com.codeheros.movieapp.modules.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codeheros.movieapp.databinding.FragmentMovieListBinding
import com.codeheros.movieapp.utils.openMovieDetails
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.codeheros.movieapp.di.ViewModelFactory
import javax.inject.Inject

/**
 * Fragment class that displays lists of movies separated by categories.
 *
 * Created by sankar on 2021-10-11.
 */
class MovieListFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var viewModel: MovieListViewModel
    private lateinit var nowPlayingRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedState: Bundle?
    ) = FragmentMovieListBinding.inflate(inflater, container, false).apply {
        AndroidSupportInjection.inject(this@MovieListFragment)

        nowPlayingRecyclerView = setupRecyclerView(nowPlayingMovieList)

        viewModel = ViewModelProviders.of(this@MovieListFragment, factory)[MovieListViewModel::class.java]
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchMovies()
    }

    private fun fetchMovies() {
        lifecycleScope.launch {
            viewModel.movies.collectLatest {
                (nowPlayingRecyclerView.adapter as MovieListPagingAdapter)?.submitData(it)
            }
        }
    }
    private fun setupRecyclerView(recyclerView: RecyclerView) = recyclerView.apply {
        layoutManager = GridLayoutManager(context,3)
        adapter = MovieListPagingAdapter { movieId, view -> openMovieDetails(movieId, view) }
    }
}