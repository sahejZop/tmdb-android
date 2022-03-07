package com.example.tmdb

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        fun openNewActivity(){
            val startAct = Intent(this, MainActivity::class.java)
            startActivity(startAct)
            finish()
        }
        Handler().postDelayed({
            openNewActivity();
        }, 2000)
    }

}