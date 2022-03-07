package com.example.tmdb.dagger

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
    MovieDatabase.DatabaseBuilder.getInstance().movieDao())

    //fun provideApi(): MovieDatabaseHelperImpl = MovieDatabaseHelperImpl()
}