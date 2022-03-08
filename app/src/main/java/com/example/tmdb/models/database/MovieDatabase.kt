package com.example.tmdb.models.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tmdb.models.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
