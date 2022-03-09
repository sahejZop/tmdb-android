package com.example.tmdb.models

import com.google.gson.annotations.SerializedName

data class TrailerDataList(
    @SerializedName("id")
    val id: Int,

    @SerializedName("results")
    val results: List<TrailerData>
)
