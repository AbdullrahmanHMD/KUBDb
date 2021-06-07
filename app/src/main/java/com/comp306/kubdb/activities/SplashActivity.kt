package com.comp306.kubdb.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.comp306.kubdb.LibraryApplication
import com.comp306.kubdb.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()


        println("CALLING FROM SPLASH")
        (application as LibraryApplication).initDatabaseWithCallback(::nextActivity)



        Handler(Looper.getMainLooper()).postDelayed({
            val toLoginActivity = Intent(this, LoginActivity::class.java)
            startActivity(toLoginActivity)
            finish()
        }, 2000)
    }

    private fun nextActivity() {

    }
}