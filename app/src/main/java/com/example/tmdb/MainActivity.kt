package com.example.tmdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tmdb.fragments.DashboardFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstfrag = DashboardFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl, firstfrag)
            commit()
        }
    }
}