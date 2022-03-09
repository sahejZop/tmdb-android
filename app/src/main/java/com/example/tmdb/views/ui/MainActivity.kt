package com.example.tmdb.views.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.tmdb.R
import com.example.tmdb.views.ui.fragments.DashboardFragment
import com.example.tmdb.views.viewmodels.DashboardViewModel
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
