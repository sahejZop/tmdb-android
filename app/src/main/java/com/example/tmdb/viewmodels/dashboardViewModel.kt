package com.example.tmdb.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tmdb.repository.Repository
import com.example.tmdb.data.MovieListData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class dashboardViewModel (private val repository: Repository): ViewModel(){

    private val _movieList = MutableLiveData<MovieListData>()
    val movieList: LiveData<MovieListData> = _movieList

    val errorMessage = MutableLiveData<String>()
    private val category = MutableLiveData<String>("popular")

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
                //TODO("Not yet implemented")
                errorMessage.postValue(t.message)
            }
        })

    }

    /*
    fun getMovieList(language: String, page: Int){
        val response = repository.getMovieList(language, page)
        response.enqueue(object : Callback<MovieListData>{
            override fun onResponse(call: Call<MovieListData>, response: Response<MovieListData>) {
                _movieList.postValue(response.body())
            }

            override fun onFailure(call: Call<MovieListData>, t: Throwable) {
                //TODO("Not yet implemented")
                errorMessage.postValue(t.message)
            }
        })

    }
    fun getTopRatedList(language: String, page: Int){
        val response = repository.getTopRatedList(language, page)
        response.enqueue(object : Callback<MovieListData>{
            override fun onResponse(call: Call<MovieListData>, response: Response<MovieListData>) {
                _movieList.postValue(response.body())
            }

            override fun onFailure(call: Call<MovieListData>, t: Throwable) {
                //TODO("Not yet implemented")
                errorMessage.postValue(t.message)
            }
        })

    }

    fun getUpcomingMovies(language: String, page: Int){
        val response = repository.getUpcomingMovies(language, page)
        response.enqueue(object : Callback<MovieListData>{
            override fun onResponse(call: Call<MovieListData>, response: Response<MovieListData>) {
                _movieList.postValue(response.body())
            }

            override fun onFailure(call: Call<MovieListData>, t: Throwable) {
                //TODO("Not yet implemented")
                errorMessage.postValue(t.message)
            }
        })

    }

     */

}
