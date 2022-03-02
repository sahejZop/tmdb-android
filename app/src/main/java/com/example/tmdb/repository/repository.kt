package com.example.tmdb.repository

import android.content.Context
import com.example.tmdb.apiServices.movieApiInterface
import com.example.tmdb.database.MovieDao
import com.example.tmdb.database.MovieDatabase
import com.example.tmdb.database.MovieDatabaseHelperImpl

class Repository(
    val movieApiInterface: movieApiInterface,
    val moviedDatabaseHelperImpl: MovieDatabaseHelperImpl
) {

    fun getMovieListquery(category: String) = movieApiInterface.getMovieListquery(category)

    /*
     fun getMovieList(language: String, page: Int) = movieApiInterface.getMovieList(language, page)
     fun getTopRatedList(language: String, page: Int) = movieApiInterface.getTopRatedList(language, page)
     fun getUpcomingMovies(language: String, page: Int) = movieApiInterface.getUpcomingMovies(language, page)
     */
}
