package com.example.tmdb.data

import com.google.gson.annotations.SerializedName

class MovieListData (
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<MovieEntity>,

    @SerializedName("total_results")
    val total_results: Int,

    @SerializedName("total_pages")
    val total_pages: Int
)