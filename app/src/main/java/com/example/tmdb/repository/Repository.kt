package com.example.tmdb.repository

import com.example.tmdb.apiServices.movieApiInterface
import com.example.tmdb.data.MovieEntity
import com.example.tmdb.database.MovieDao
import javax.inject.Inject

class Repository
    @Inject constructor(
    private val movieApiInterface: movieApiInterface,
    private val movieDao: MovieDao
) {

    fun getMovieListQuery(category: String) = movieApiInterface.getMovieListquery(category)
    suspend fun isMovieInTable(id: String) = movieDao.isMovieInTable(id)
    suspend fun getMovies(): List<MovieEntity> = movieDao.getMovies()
    suspend fun deleteMovie(movieclass: MovieEntity) = movieDao.deleteMovie(movieclass)
    suspend fun insertMovie(movieclass: MovieEntity) = movieDao.insertMovie(movieclass)

    }


