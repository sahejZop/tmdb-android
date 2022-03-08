package com.example.tmdb.views.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.models.MovieEntity
import com.example.tmdb.models.MovieListData
import com.example.tmdb.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: Repository
) :
    ViewModel() {

    var currentPage: Int = 1
    var currentChip: String = "popular"

    private val _movieList = MutableLiveData<MovieListData>()
    val movieList: LiveData<MovieListData> = _movieList

    val errorMessage = MutableLiveData<String>()
    private val category = MutableLiveData<String>("popular")

    private val _favMovies = MutableLiveData<List<MovieEntity>>()
    val favMovies: LiveData<List<MovieEntity>> = _favMovies

    private val _currentMovie = MutableLiveData<String>()

    private val _isFav = MutableLiveData<Boolean?>(false)
    val isFav: LiveData<Boolean?> = _isFav

    var oldList: MovieListData? = null

    fun onFavButtonPress(movieclass: MovieEntity) {
        viewModelScope.launch {
            if (!isFav.value!!) {
                repository.insertMovie(movieclass)
                _isFav.value = true
            } else {
                repository.deleteMovie(movieclass)
                _isFav.value = false
            }
        }
    }

    fun showFavourite() {
        viewModelScope.launch {
            _favMovies.postValue(repository.getMovies())
        }
    }

    fun changeMovie(string: String) {
        viewModelScope.launch {
            _currentMovie.value = string
            _isFav.value = isMovieInTable(_currentMovie.value!!)
        }
    }

    fun changeCategory(chipCategory: String) {
        category.value = chipCategory
        oldList = null
        currentPage = 1
        currentChip = chipCategory
        getMovieListQuery()
    }

    fun getMovieListQuery() {
        val response = repository.getMovieListQuery(category.value.toString(), currentPage)
        response.enqueue(object : Callback<MovieListData> {
            override fun onResponse(call: Call<MovieListData>, response: Response<MovieListData>) {
                if (oldList == null) {
                    oldList = response.body()
                } else {
                    oldList!!.results.addAll(response.body()!!.results)
                    oldList!!.apply {
                        page++
                    }
                }
                Log.d("vm", response.body()!!.results.toString())
                Log.d("vm", oldList!!.results.size.toString())
                currentPage++
                _movieList.postValue(oldList)
            }

            override fun onFailure(call: Call<MovieListData>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    private suspend fun isMovieInTable(id: String): Boolean {
        val movie = repository.isMovieInTable(id)

        if (movie > 0)
            return true
        return false
    }
}
