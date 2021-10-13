package com.codeheros.movieapp.remote.map

import com.codeheros.movieapp.model.MovieDetails
import com.codeheros.movieapp.remote.model.MovieGenre
import com.codeheros.movieapp.remote.model.MovieSummary
import java.text.SimpleDateFormat
import java.util.*

class MovieDetailsMapper(private val baseImageURL: String) : Mapper<MovieSummary, MovieDetails> {

    companion object {
        private const val RELEASE_DATE_UI_FORMAT = "MMMM dd, yyyy"
        private const val RELEASE_DATE_REMOTE_FORMAT = "yyyy-MM-dd"
    }
    override fun map(remote: MovieSummary) = MovieDetails(
        remote.id ?: 0,
        remote.title ?: "",
        remote.runtime ?: 0,
        remote.votesCount ?: 0,
        extractGenresNames(remote.genres ?: emptyList()),
        "$baseImageURL${remote.poster}",
        remote.overview ?: "",
        getUIReleaseDate(remote.releaseDate)
    )

    private fun extractGenresNames(list: List<MovieGenre>) = list.map {
        it.name ?: ""
    }

    private fun getUIReleaseDate(releaseDate: String?): String = synchronized(this) {
        val uiFormatter = SimpleDateFormat(MovieDetailsMapper.RELEASE_DATE_UI_FORMAT, Locale.getDefault())

        return try {
            val date = SimpleDateFormat(MovieDetailsMapper.RELEASE_DATE_REMOTE_FORMAT, Locale.getDefault())
                .parse(releaseDate ?: "2000-01-01") ?: Date()
            uiFormatter.format(date)
        } catch (e: Exception) {
            uiFormatter.format(Date())
        }
    }
}