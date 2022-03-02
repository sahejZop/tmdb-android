package com.example.tmdb.database

class MovieDatabaseHelperImpl(
    private val movieDao: MovieDao
): MovieDatabaseHelper {

    override suspend fun getMovie(movieId: String): MovieEntity = movieDao.getMovie(movieId)

    override suspend fun getMovies(): List<MovieEntity> {
        return movieDao.getMovies()
    }

    override suspend fun insertMovie(movie: MovieEntity) {
        movieDao.insertMovie(movie)
    }

    override suspend fun deleteMovie(movie: MovieEntity) {
        movieDao.deleteMovie(movie)
    }

    override suspend fun isMovieInTable(movieId: String): Int{
        return movieDao.isMovieInTable(movieId)
    }
}
