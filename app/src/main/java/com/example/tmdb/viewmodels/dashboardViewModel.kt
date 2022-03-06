package com.example.tmdb.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdb.repository.Repository
import com.example.tmdb.data.MovieListData
import com.example.tmdb.database.MovieEntity
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class dashboardViewModel (val repository: Repository): ViewModel(){

    var onFav: Boolean = false

    private val _movieList = MutableLiveData<MovieListData>()
    val movieList: LiveData<MovieListData> = _movieList

    val errorMessage = MutableLiveData<String>()
    private val category = MutableLiveData<String>("popular")

    private val _favMovies = MutableLiveData<List<MovieEntity>>()
    val favMovies: LiveData<List<MovieEntity>> = _favMovies

    private val _currentMovie  = MutableLiveData<String>()

    private val _isFav = MutableLiveData<Boolean>(false)
    val isFav: LiveData<Boolean> = _isFav

    fun onFavButtonPress(movieclass: MovieEntity){
        viewModelScope.launch {
            if (!isFav.value!!){
                repository.moviedDatabaseHelperImpl.insertMovie(movieclass)
                _isFav.value = true
            }
            else{
                repository.moviedDatabaseHelperImpl.deleteMovie(movieclass)
                _isFav.value = false
            }
        }
    }

    fun showFavourite(){
        lateinit var data: List<MovieEntity>
        viewModelScope.launch {
            data = repository.moviedDatabaseHelperImpl.getMovies()
            _favMovies.postValue(data)
            //_favMovies.postValue(repository.moviedDatabaseHelperImpl.getMovies())
        }
    }

    fun changeMovie(string: String){
        viewModelScope.launch {
            //_currentMovie.postValue(string)
            _currentMovie.value = string
            //_isFav.postValue(isMovieInTable(_currentMovie.value!!)!!)
            _isFav.value = isMovieInTable(_currentMovie.value!!)!!
        }
    }

    fun changeCategory(string: String){
        category.value = string
        getMovieListquery(string)
    }


    fun getMovieListquery(category: String){
        val response = repository.getMovieListquery(category)
        response.enqueue(object : Callback<MovieListData>{
            override fun onResponse(call: Call<MovieListData>, response: Response<MovieListData>) {
                _movieList.postValue(response.body())
            }

            override fun onFailure(call: Call<MovieListData>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    suspend fun isMovieInTable(id: String): Boolean{
        val movie = repository.moviedDatabaseHelperImpl.isMovieInTable(id)

        if (movie > 0)
            return true
        return false
    }
}
