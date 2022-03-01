package com.example.tmdb.database

import android.content.Context
import com.example.tmdb.data.MovieData
import com.example.tmdb.data.MovieDatabaseHelper

class MovieDatabaseHelperImpl(
    private val movieDao: MovieDao
): MovieDatabaseHelper {
    override suspend fun getMovie(movieId: String): Movie = movieDao.getMovie(movieId)

    override suspend fun getMovies(): List<Movie> {
        return movieDao.getMovies()
    }

    override suspend fun insertMovie(movie: Movie) {
        movieDao.insertMovie(movie)
    }

    override suspend fun deleteMovie(movie: Movie) {
        movieDao.deleteMovie(movie)
    }
}
