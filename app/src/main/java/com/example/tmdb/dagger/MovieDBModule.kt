package com.example.tmdb.dagger

import android.content.Context
import com.example.tmdb.MainActivity
import com.example.tmdb.database.MovieDao
import com.example.tmdb.database.MovieDatabase
import com.example.tmdb.database.MovieDatabaseHelperImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MovieDBModule {

    @Provides
    @Singleton
    fun provideApi(): MovieDatabaseHelperImpl = MovieDatabaseHelperImpl(
    MovieDatabase.DatabaseBuilder.getInstance(applicationcontext).movieDao()
}
