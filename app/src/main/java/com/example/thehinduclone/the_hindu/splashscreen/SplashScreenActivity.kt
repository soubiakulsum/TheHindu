package com.example.thehinduclone.the_hindu.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.os.HandlerCompat.postDelayed
import com.example.thehinduclone.R
import com.example.thehinduclone.the_hindu.activities.MainScreenActivity

class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_TIME:Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            startActivity(Intent (applicationContext,MainScreenActivity::class.java))
            finish()
        },SPLASH_TIME)
    }
}


