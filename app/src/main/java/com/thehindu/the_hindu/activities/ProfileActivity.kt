package com.thehindu.the_hindu.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.thehindu.R
import com.thehindu.thehinduclone.ui.home.SideNavActivity
import com.thehindu.themain.LocalConstants
import com.thehindu.themain.PreferenceHelper
import com.thehindu.themain.models.UserResponse
import com.thehindu.themain.services.ClientCalls
import kotlinx.android.synthetic.main.activity_localize_home.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class ProfileActivity : AppCompatActivity() {
    val clientCalls = ClientCalls.getInstances()
    lateinit var user: UserResponse
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        tvMyAccounts.setOnClickListener {
            startActivity(Intent(this, SideNavActivity::class.java))
            finish()
        }
        PreferenceHelper.getSharedPreferences(this)
        if (PreferenceHelper.readBooleanFromPreference(LocalConstants.PREF_TOKEN_BOOLEAN)) {
            LocalConstants.API_CLIENT.getUser(
                "Bearer " + PreferenceHelper.readStringFromPreference(
                    LocalConstants.PREF_TOKEN_VALUE
                )
            ).enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                }

                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    try {
                        user = response.body()!!
                        etFirstName.setText(user.name.toString())
                        etEmail.setText(user.email.toString())
                        etEmail.isEnabled = false
                        etMobile.setText(user.phone.toString())
                        etBirth.setText(LocalDate.now().toString())
                        etCity.setText(user.address.toString())
                        Log.d("TAG", "onResponse: " + user.email)

                    } catch (e: Exception) {
                        Log.d("TAG", "onResponse: " + e)
                    }
                }
            })

            saveChanges.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    LocalConstants.API_CLIENT.updateUser(
                        "Bearer " + PreferenceHelper.readStringFromPreference(LocalConstants.PREF_TOKEN_VALUE),
                        etFirstName.text.toString(),
                        etMobile.text.toString(),
                        etBirth.text.toString()
                    )
                    CoroutineScope(Dispatchers.Main).launch {
                        Toast.makeText(this@ProfileActivity, "Updated", Toast.LENGTH_SHORT)
                            .show()
                        finish()
                    }
                }
            }
        }
    }
}