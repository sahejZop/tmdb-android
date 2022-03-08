package com.example.tmdb.database

import com.example.tmdb.data.MovieEntity

class MovieDatabaseHelperImpl(
    private val movieDao: MovieDao
){

    //@Inject lateinit var movieDaoInject: MovieDao

    suspend fun getMovie(movieId: String): MovieEntity = movieDao.getMovie(movieId)

    suspend fun getMovies(): List<MovieEntity> {
        return movieDao.getMovies()
    }

    suspend fun insertMovie(movie: MovieEntity) {
        movieDao.insertMovie(movie)
    }

    suspend fun deleteMovie(movie: MovieEntity) {
        movieDao.deleteMovie(movie)
    }

    suspend fun isMovieInTable(movieId: String): Int{
        return movieDao.isMovieInTable(movieId)
    }
}
