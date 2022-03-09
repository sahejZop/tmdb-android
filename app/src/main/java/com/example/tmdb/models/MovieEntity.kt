package com.example.tmdb.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieEntity(
    @SerializedName("id")
    @PrimaryKey val id: Int,

    @SerializedName("title")
    @ColumnInfo(name = "title") val title: String,

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average") val vote_average: String,

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path") val backdrop_path: String,

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path") val poster_path: String,

    @SerializedName("overview")
    @ColumnInfo(name = "overview") val overview: String,

    @SerializedName("release_date")
    @ColumnInfo(name = "release_date") val release_date: String
)
