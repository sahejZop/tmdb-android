package com.example.tmdb.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmdb.repository.Repository
import java.lang.IllegalArgumentException

class MyViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            DashboardViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("viewmodel not found")
        }
    }
}