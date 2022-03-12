package com.example.tmdb.views.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.models.MovieEntity
import com.example.tmdb.models.MovieListData
import com.example.tmdb.models.TrailerDataList
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

    private val _favMovies = MutableLiveData<List<MovieEntity>>()
    val favMovies: LiveData<List<MovieEntity>> = _favMovies

    private val _trailer = MutableLiveData<String>()
    val trailer: LiveData<String> = _trailer

    private val _isFav = MutableLiveData(false)
    val isFav: LiveData<Boolean?> = _isFav

    var movieListData: MovieListData? = null

    fun onFavButtonPress(movieEntity: MovieEntity) {
        viewModelScope.launch {
            if (!isFav.value!!) {
                repository.insertMovie(movieEntity)
                _isFav.value = true
            } else {
                repository.deleteMovie(movieEntity)
                _isFav.value = false
            }
        }
    }

    fun showFavourite() {
        viewModelScope.launch {
            _favMovies.postValue(repository.getMovies())
        }
    }

    fun changeMovie(movieId: String) {
        viewModelScope.launch {
            _isFav.value = isMovieInTable(movieId)
        }
    }

    fun changeCategory(chipCategory: String) {
        movieListData = null
        currentPage = 1
        currentChip = chipCategory
        getMovieListQuery()
    }

    fun getMovieListQuery() {
        val response = repository.getMovieListQuery(currentChip, currentPage)
        response.enqueue(object : Callback<MovieListData> {
            override fun onResponse(call: Call<MovieListData>, response: Response<MovieListData>) {
                if (movieListData == null) {
                    movieListData = response.body()
                } else {
                    movieListData!!.apply {
                        results.addAll(response.body()!!.results)
                        page++
                    }
                }
                currentPage++
                _movieList.postValue(movieListData)
            }

            override fun onFailure(call: Call<MovieListData>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun getTrailer(movieId: String) {
        val response = repository.getTrailer(movieId)
        response.enqueue(object : Callback<TrailerDataList> {
            override fun onResponse(
                call: Call<TrailerDataList>,
                response: Response<TrailerDataList>
            ) {
                _trailer.value = response.body()!!.results[0].key
            }

            override fun onFailure(call: Call<TrailerDataList>, t: Throwable) {
                Log.d("vm", "fail")
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
