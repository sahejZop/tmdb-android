package com.example.tmdb.views.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.tmdb.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        fun openNewActivity() {
            val startAct = Intent(this, MainActivity::class.java)
            startActivity(startAct)
            finish()
        }
        Handler(Looper.getMainLooper()).postDelayed({
            openNewActivity()
        }, 2000)
    }
}
