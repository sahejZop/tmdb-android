package com.example.tmdb.application

import android.app.Application
import com.example.tmdb.dagger.AppComponent
import com.example.tmdb.dagger.AppModule
import com.example.tmdb.dagger.DaggerAppComponent

class TmdbApplication : Application() {

    var tmdbComponent = initDagger(this)

    private fun initDagger(app: TmdbApplication): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()
}