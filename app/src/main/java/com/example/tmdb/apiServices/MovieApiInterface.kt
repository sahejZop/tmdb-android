package com.example.tmdb.apiServices

import com.example.tmdb.models.MovieListData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiInterface {

    @GET("movie/{category}?api_key=286fe5ed7f42deec75f227d9df455b36&language=en-US")
    fun getMovieListQuery(
        @Path("category")
        category: String,
        @Query("page")
        page: Int
    ): Call<MovieListData>
}
