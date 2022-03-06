package com.example.tmdb.dagger

import com.example.tmdb.MainActivity
import com.example.tmdb.apiServices.movieApiInterface
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent{
    //fun getApi(target: MainActivity): movieApiInterface
    fun inject(target: MainActivity)
}