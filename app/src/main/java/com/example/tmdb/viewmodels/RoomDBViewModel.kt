package com.example.tmdb.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.data.MovieData
import com.example.tmdb.database.Movie
import com.example.tmdb.database.MovieDatabaseHelperImpl
import kotlinx.coroutines.launch

class RoomDBViewModel(private val dbhelper: MovieDatabaseHelperImpl): ViewModel() {
    private fun getMovies(){
        viewModelScope.launch {
            try {
                val movies: List<Movie> = dbhelper.getMovies()
            }
            catch (e: Exception){

            }
        }
    }
}