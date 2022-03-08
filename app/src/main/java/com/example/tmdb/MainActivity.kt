package com.example.tmdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.tmdb.fragments.DashboardFragment
import com.example.tmdb.viewmodels.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val dashboardViewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFragment = DashboardFragment(viewModel = dashboardViewModel)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl, firstFragment)
            commit()
        }
    }

}