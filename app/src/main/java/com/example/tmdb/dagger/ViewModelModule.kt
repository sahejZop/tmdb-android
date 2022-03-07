package com.example.tmdb.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmdb.viewmodels.MyViewModelFactory
import com.example.tmdb.viewmodels.dashboardViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/*
@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun provideVM(): dashboardViewModel = ViewModelProvider(this, MyViewModelFactory(repository))[dashboardViewModel::class.java]
}
 */