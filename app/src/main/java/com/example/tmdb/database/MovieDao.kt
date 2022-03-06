package com.example.tmdb.database

import androidx.room.*

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    suspend fun getMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

    @Query("SELECT * FROM movies where id = :movieId")
    suspend fun getMovie(movieId: String): MovieEntity

    @Query("SELECT count(*) FROM movies where id = :movieId")
    suspend fun isMovieInTable(movieId: String): Int
}