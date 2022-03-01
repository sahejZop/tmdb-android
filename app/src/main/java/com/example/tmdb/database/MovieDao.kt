package com.example.tmdb.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.tmdb.data.MovieData

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    suspend fun getMovies(): List<Movie>

    @Insert
    suspend fun insertMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM movies where id = :movieId")
    suspend fun getMovie(movieId: String): Movie
}