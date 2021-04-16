package com.thehindu.themain.services

import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.thehindu.R
import com.thehindu.themain.LocalConstants
import com.thehindu.themain.PreferenceHelper
import com.thehindu.themain.models.NewsResponse
import com.thehindu.themain.models.tokenreqres.JwtRequest
import com.thehindu.themain.models.tokenreqres.JwtResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class ClientCalls {
    private val newsResponseList = mutableListOf<NewsResponse>()

    companion object {
        var instance: ClientCalls? = null

        fun getInstances(): ClientCalls {
            if (instance != null) return instance as ClientCalls
            else instance = ClientCalls()
            return instance as ClientCalls;
        }
    }

    fun handleSignInResult(result: GoogleSignInResult): Boolean {
        if (result.isSuccess) {
            val account = result.signInAccount
            val email = account?.email!!

            LocalConstants.API_CLIENT.isUserPresent(email, email)
                .enqueue(object : Callback<Boolean> {
                    override fun onFailure(call: Call<Boolean>, t: Throwable) {

                    }

                    override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                        if (!response.body()!!) {
                            CoroutineScope(Dispatchers.IO).launch {
                                LocalConstants.API_CLIENT.sign_up(
                                    account.displayName!!,
                                    email,
                                    email
                                )
                                CoroutineScope(Dispatchers.Main).launch {
                                    val jwtRequest =
                                        JwtRequest(
                                            email, email
                                        )
                                    loginToGetToken(jwtRequest)
                                }
                            }
                        } else {
                            val jwtRequest =
                                JwtRequest(
                                    email, email
                                )
                            loginToGetToken(jwtRequest)
                        }
                    }
                })
            return true;
        } else {
            return false
        }
    }

    private fun loginToGetToken(jwtRequest: JwtRequest) {
        LocalConstants.API_CLIENT.hitApi(jwtRequest).enqueue(object : Callback<JwtResponse> {
            override fun onFailure(call: Call<JwtResponse>, t: Throwable) {
                Log.d("TAG", "onFailure: Failed to login")
            }

            override fun onResponse(
                call: Call<JwtResponse>,
                response: Response<JwtResponse>
            ) {
                Log.d("TAG", "onResponse: json " + response.body())
                PreferenceHelper.writeBooleanToPreference(
                    LocalConstants.PREF_TOKEN_BOOLEAN,
                    true
                );
                PreferenceHelper.writeStringToPreference(
                    LocalConstants.PREF_TOKEN_VALUE,
                    response.body()?.token.toString()
                )
                PreferenceHelper.writeBooleanToPreference(
                    LocalConstants.PREF_USER_LOGIN,
                    true
                )
            }
        })
    }

    fun getAllNews(): List<NewsResponse> {

        LocalConstants.API_CLIENT.getAllNews(
            "Bearer " + PreferenceHelper.readStringFromPreference(
                LocalConstants.PREF_TOKEN_VALUE
            )
        )
            .enqueue(object : Callback<List<NewsResponse>> {
                override fun onFailure(call: Call<List<NewsResponse>>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<List<NewsResponse>>,
                    response: Response<List<NewsResponse>>
                ) {
                    handleNewsResponse(response)
                }
            })
        Log.d(
            "TAG",
            "getAllNews: " + "Bearer " + PreferenceHelper.readStringFromPreference(LocalConstants.PREF_TOKEN_VALUE)
        )
        return newsResponseList;
    }

    fun handleNewsResponse(response: Response<List<NewsResponse>>) {
        if (response.body() != null) {
            newsResponseList.clear()
            val a = response.body()
            newsResponseList.addAll(a!!)
        } else {
            Log.d(
                "TAG",
                "onResponse: token" + PreferenceHelper.readStringFromPreference(
                    LocalConstants.PREF_TOKEN_VALUE
                )
            )
            Log.d("TAG", "onResponse: body" + response.body())
            newsResponseList.clear();
        }
    }


    fun isNewsExist(v: View, id: Int?) {
        val c = LocalConstants.API_CLIENT.isNewsPresent(
            "Bearer " + PreferenceHelper.readStringFromPreference(
                LocalConstants.PREF_TOKEN_VALUE
            ), id
        )

        c.enqueue(object : Callback<Boolean> {
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Toast.makeText(v.context, "Error while Saving the read list", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                CoroutineScope(Dispatchers.Main).launch {
                    if (!response.body()!!) {
                        Toast.makeText(v.context, "Added to the read list", Toast.LENGTH_SHORT)
                            .show()
                        v.setBackgroundResource(R.drawable.ic_baseline_bookmark_24)
                    } else {
                        Toast.makeText(v.context, "Already in the read list", Toast.LENGTH_SHORT)
                            .show()
                        v.setBackgroundResource(R.drawable.ic_baseline_bookmark_border_24)
                    }
                }
            }
        })
    }
}