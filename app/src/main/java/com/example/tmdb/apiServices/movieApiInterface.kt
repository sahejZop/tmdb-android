package com.example.tmdb.apiServices

import com.example.tmdb.data.MovieListData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface movieApiInterface {

    //@GET("/movielist.json")

    @GET("movie/{category}?api_key=286fe5ed7f42deec75f227d9df455b36&language=en-US")
    fun getMovieListquery(
        @Path("category")
        category: String
    ): Call<MovieListData>

    /*
    @GET("movie/now_playing?api_key=55157ca6962686fb13267f7f9342c2d5")
    fun getMovieList(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Call<MovieListData>

    @GET("movie/top_rated?api_key=55157ca6962686fb13267f7f9342c2d5")
    fun getTopRatedList(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Call<MovieListData>

    @GET("movie/upcoming?api_key=55157ca6962686fb13267f7f9342c2d5")
    fun getUpcomingMovies(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Call<MovieListData>

     */
}