package com.example.tmdb.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tmdb.repository.repository
import com.example.tmdb.data.MovieListData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class dashboardViewModel constructor(private val repository: repository): ViewModel(){

    private val _movieList = MutableLiveData<MovieListData>()
    val movieList: LiveData<MovieListData> = _movieList

    val errorMessage = MutableLiveData<String>()
    private val category = MutableLiveData<String>("popular")

    fun changeCategory(string: String){
        category.value = string
        getMovieList()
    }

    fun getMovieList(){
        val response = repository.getMovieList()
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

}
