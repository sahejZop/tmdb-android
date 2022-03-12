package com.example.tmdb.repository

import com.example.tmdb.apiServices.MovieApiInterface
import com.example.tmdb.models.MovieEntity
import com.example.tmdb.models.database.MovieDao
import javax.inject.Inject

class Repository
@Inject constructor(
    private val movieApiInterface: MovieApiInterface,
    private val movieDao: MovieDao
) {

    fun getMovieListQuery(category: String, page: Int) = movieApiInterface.getMovieListQuery(category, page)
    fun getTrailer(movieId: String) = movieApiInterface.getTrailer(movieId)
    suspend fun isMovieInTable(id: String) = movieDao.isMovieInTable(id)
    suspend fun getMovies(): List<MovieEntity> = movieDao.getMovies()
    suspend fun deleteMovie(movieClass: MovieEntity) = movieDao.deleteMovie(movieClass)
    suspend fun insertMovie(movieClass: MovieEntity) = movieDao.insertMovie(movieClass)
}
