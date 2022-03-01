package com.example.tmdb.data

import com.example.tmdb.database.Movie

interface MovieDatabaseHelper {
    suspend fun getMovie(movieId: String): Movie
    suspend fun getMovies(): List<Movie>
    suspend fun insertMovie(movie: Movie)
    suspend fun deleteMovie(movie: Movie)
}