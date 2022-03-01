package com.example.tmdb.data


data class MovieData(
    val id: Int,
    val title: String,
    val vote_average: String,
    val backdrop_path: String,
    val poster_path: String,
    val overview: String,
    val release_date: String
    )
