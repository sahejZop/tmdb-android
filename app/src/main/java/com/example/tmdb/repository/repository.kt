package com.example.tmdb.repository

import com.example.tmdb.apiServices.movieApiInterface
import com.example.tmdb.database.MovieDatabaseHelperImpl
import javax.inject.Inject

class Repository(
    //@Inject mo
    val movieApiInterface: movieApiInterface,
    val moviedDatabaseHelperImpl: MovieDatabaseHelperImpl
) {
    fun getMovieListquery(category: String) = movieApiInterface.getMovieListquery(category)
}
