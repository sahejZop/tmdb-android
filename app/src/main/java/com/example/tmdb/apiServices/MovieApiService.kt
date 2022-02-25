package com.example.tmdb.apiServices

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieApiInstance {
    val api: movieApiInterface by lazy {
        Retrofit.Builder()
            //.baseUrl("https://howtodoandroid.com")
            //.baseUrl("https://api.themoviedb.org/")
            //.baseUrl("https://jsonplaceholder.typicode.com")
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(movieApiInterface::class.java)
    }
}
