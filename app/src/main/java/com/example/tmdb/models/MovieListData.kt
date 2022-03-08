package com.example.tmdb.models

import com.google.gson.annotations.SerializedName

class MovieListData(
    @SerializedName("page")
    var page: Int,

    @SerializedName("results")
    var results: MutableList<MovieEntity>,

    @SerializedName("total_results")
    var total_results: Int,

    @SerializedName("total_pages")
    var total_pages: Int
)
