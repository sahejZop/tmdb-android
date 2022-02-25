package com.example.tmdb.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tmdb.repository.repository
import com.example.tmdb.data.MovieListData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class dashboardViewModel constructor(private val repository: repository): ViewModel(){

    val movieList = MutableLiveData<MovieListData>()
    val errorMessage = MutableLiveData<String>()

    fun getMovieList(){

        val response = repository.getMovieList()
        response.enqueue(object : Callback<MovieListData>{
            override fun onResponse(call: Call<MovieListData>, response: Response<MovieListData>) {
                movieList.postValue(response.body())
            }

            override fun onFailure(call: Call<MovieListData>, t: Throwable) {
                //TODO("Not yet implemented")
                errorMessage.postValue(t.message)
            }
        })

    }

}
