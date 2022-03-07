package com.example.tmdb.repository

import com.example.tmdb.apiServices.movieApiInterface
import com.example.tmdb.application.TmdbApplication
import com.example.tmdb.database.MovieDatabaseHelperImpl
import javax.inject.Inject

class Repository(
    val movieDatabaseHelperImpl: MovieDatabaseHelperImpl
) {
    @Inject lateinit var movieApiInterface: movieApiInterface
    //@Inject lateinit var movieDatabaseHelperImpl: MovieDatabaseHelperImpl
    val temp = TmdbApplication().tmdbComponent.injectRep(this)

    fun getMovieListquery(category: String) = movieApiInterface.getMovieListquery(category)
}
