package com.example.tmdb.repository

import com.example.tmdb.apiServices.movieApiInterface

class repository constructor(private val movieApiInterface: movieApiInterface){

    fun getMovieList() = movieApiInterface.getMovieList()

}