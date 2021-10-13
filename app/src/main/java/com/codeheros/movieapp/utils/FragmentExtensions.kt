package com.codeheros.movieapp.utils


import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.codeheros.movieapp.R
import com.codeheros.movieapp.modules.movies.MovieListFragmentDirections


fun Fragment.openMovieDetails(movieId: Int, moviePosterId: View) {
    val args = MovieListFragmentDirections.openMovieDetails(movieId).arguments
    val extras = FragmentNavigatorExtras(
        moviePosterId to "moviePosterView"
    )
    findNavController().navigate(R.id.movieDetailsFragment, args, null, extras)

}