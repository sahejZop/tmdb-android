package com.example.tmdb.dagger

import com.example.tmdb.apiServices.movieApiInterface
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideApi(): movieApiInterface = movieApiInterface.getInstance()
}