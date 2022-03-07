package com.example.tmdb.dagger

import android.content.Context
import com.example.tmdb.database.MovieDao
import com.example.tmdb.database.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MovieDaoModule {

    @Provides
    @Singleton
    fun provideDao(): MovieDao = MovieDatabase.DatabaseBuilder.getInstance().movieDao()
}
