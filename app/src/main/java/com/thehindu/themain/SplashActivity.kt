package com.thehindu.themain

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.thehindu.R
import com.thehindu.the_hindu.activities.MainActivity2
import com.thehindu.the_hindu.activities.MainScreenActivity
import com.thehindu.themain.services.ClientCalls
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    val clientCalls = ClientCalls.getInstances()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        PreferenceHelper.getSharedPreferences(this)

        progressBar2.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO).launch {
            delay(1500)
            if (PreferenceHelper.readBooleanFromPreference(LocalConstants.PREF_TOKEN_BOOLEAN)) {
                clientCalls.checkToken(
                    "Bearer " +
                            PreferenceHelper.readStringFromPreference(
                                LocalConstants.PREF_TOKEN_VALUE
                            ), progressBar2
                )
                val intent = Intent(this@SplashActivity, MainActivity2::class.java)
                startActivity(intent)
                finish()
                val booleanValue =
                    PreferenceHelper.readBooleanFromPreference("night_mode")
                if (booleanValue) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
            } else {
                val intent = Intent(this@SplashActivity, MainScreenActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}