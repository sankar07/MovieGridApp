package com.codeheros.movieapp.modules.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.transition.TransitionInflater
import com.codeheros.movieapp.databinding.FragmentMovieDetailsBinding
import dagger.android.support.AndroidSupportInjection
import com.codeheros.movieapp.model.Result
import com.codeheros.movieapp.di.ViewModelFactory
import javax.inject.Inject

class MovieDetailsFragment : Fragment() {
    @Inject
    lateinit var factory: ViewModelFactory
    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var dataBinding: FragmentMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.explode)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentMovieDetailsBinding.inflate(inflater, container, false).also {
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProviders.of(this, factory)[MovieDetailsViewModel::class.java]
        dataBinding = it
    }.root
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMovieDetails(arguments?.getInt("movieId") ?: 0)


    }


    private fun getMovieDetails(movieId: Int) = viewModel.getMovieDetails(movieId).observe(this,
    Observer {
        when(it.status){
            Result.Status.OK -> it.payload?.run {
                dataBinding.movie = this
            }
            Result.Status.ERROR -> Log.d(this.javaClass.name, it.error ?: "")
            Result.Status.LOADING -> Log.d(this.javaClass.name, "Loading")
        }
    })


}