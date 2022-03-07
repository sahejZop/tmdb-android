package com.example.tmdb

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.tmdb.apiServices.movieApiInterface
import com.example.tmdb.application.TmdbApplication
import com.example.tmdb.database.MovieDatabase
import com.example.tmdb.database.MovieDatabaseHelperImpl
import com.example.tmdb.fragments.DashboardFragment
import com.example.tmdb.repository.Repository
import com.example.tmdb.viewmodels.MyViewModelFactory
import com.example.tmdb.viewmodels.dashboardViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var repository: Repository
    @Inject lateinit var movieApiInterfaceObj: movieApiInterface
    //@Inject lateinit var movieDatabaseHelperImpl: MovieDatabaseHelperImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tmdbAppObj = TmdbApplication()
        tmdbAppObj.tmdbComponent.inject(this)

        //(application as TmdbApplication).tmdbComponent.inject(this)

        //val movieApiInterfaceObj = movieApiInterface.getInstance()
        repository = Repository(movieApiInterfaceObj,
            moviedDatabaseHelperImpl = MovieDatabaseHelperImpl(
            MovieDatabase.DatabaseBuilder.getInstance(applicationContext).movieDao()
        ))

        /*
        repository = Repository(
            movieApiInterface = movieApiInterface.getInstance(),
            moviedDatabaseHelperImpl = MovieDatabaseHelperImpl(
                MovieDatabase.DatabaseBuilder.getInstance(applicationContext).movieDao()
            )
        )
         */
        val dashboardViewModel = ViewModelProvider(this, MyViewModelFactory(repository))[dashboardViewModel::class.java]
        val firstfrag = DashboardFragment(viewModel = dashboardViewModel)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl, firstfrag)
            commit()
        }
    }

}