package com.codeheros.movieapp.modules.movies

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.codeheros.movieapp.databinding.ItemMovieBinding
import com.codeheros.movieapp.model.Movie
import com.codeheros.movieapp.utils.layoutInflater

class MovieListPagingAdapter(private val onMovieSelected: (Int, View) -> Unit = { movieId, view -> })
    : PagingDataAdapter<Movie, MovieListPagingAdapter.MovieViewHolder> (MovieComparator){


    override fun onBindViewHolder(holder: MovieViewHolder,
                                  position: Int) = holder.binding.run {
        movie = getItem(position)
        executePendingBindings()
        root.setOnClickListener { onMovieSelected(movie?.id ?: 0, holder.binding.moviePoster) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder = MovieViewHolder(
        ItemMovieBinding.inflate(parent.layoutInflater, parent, false)
    )


inner class MovieViewHolder(internal val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)
    object MovieComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id== newItem.id
        }
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

}