package com.thehindu.themain.services

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.thehindu.R
import com.thehindu.thehinduclone.ui.home.SideNavActivity
import com.thehindu.thehinduclone.ui.menu.CovidFragment
import com.thehindu.thehinduclone.ui.menu.HomeFragment
import com.thehindu.themain.LocalConstants
import com.thehindu.themain.PreferenceHelper
import com.thehindu.themain.localdatabase.savedlist.SavedEntity
import com.thehindu.themain.models.NewsResponse
import com.thehindu.themain.models.tokenreqres.JwtRequest
import com.thehindu.themain.models.tokenreqres.JwtResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

open class ClientCalls {
    private val newsResponseList = mutableListOf<NewsResponse>()

    companion object {
        var instance: ClientCalls? = null
        var x = mutableListOf<NewsResponse>()
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

    fun checkUserAndSignIn(email: String, pass: String) {
        if (email != "" && pass != "") {
            LocalConstants.API_CLIENT.isUserPresent(email, pass)
                .enqueue(object : Callback<Boolean> {
                    override fun onFailure(call: Call<Boolean>, t: Throwable) {

                    }

                    override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                        if (!response.body()!!) {
                            CoroutineScope(Dispatchers.IO).launch {
                                LocalConstants.API_CLIENT.sign_up(
                                    email,
                                    email,
                                    pass
                                )
                                CoroutineScope(Dispatchers.Main).launch {
                                    val jwtRequest =
                                        JwtRequest(
                                            email, pass
                                        )
                                    loginToGetToken(jwtRequest)
                                }
                            }
                        } else {
                            val jwtRequest =
                                JwtRequest(
                                    email, pass
                                )
                            loginToGetToken(jwtRequest)
                        }
                    }
                })
            return;
        } else {
            return
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
                )
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

    fun getSpecificNews(id: Int, str: String): List<NewsResponse> {
        CoroutineScope(Dispatchers.IO).launch {
            LocalConstants.API_CLIENT.getSpecificNews(
                "Bearer " + PreferenceHelper.readStringFromPreference(
                    LocalConstants.PREF_TOKEN_VALUE
                ),
                id,
                str
            ).enqueue(object : Callback<List<NewsResponse>> {
                override fun onFailure(call: Call<List<NewsResponse>>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<List<NewsResponse>>,
                    response: Response<List<NewsResponse>>
                ) {
                    Log.d("TAG", "onResponse: " + id);
                    x.clear()
                    val y = response.body()
                    if (y != null) {
                        x.addAll(y)
                    }
                    if (SideNavActivity.newsRvAdapter != null)
                        SideNavActivity.newsRvAdapter.notifyDataSetChanged()
                }
            })
        }
        return x
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
        val token = "Bearer " + PreferenceHelper.readStringFromPreference(
            LocalConstants.PREF_TOKEN_VALUE
        )
        val c = LocalConstants.API_CLIENT.isNewsPresent(
            token
            , id
        )

        c.enqueue(object : Callback<Boolean> {
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Toast.makeText(v.context, "Error while Saving the read list", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (!response.body()!!) {
                    v.setBackgroundResource(R.drawable.ic_baseline_bookmark_24)
                    Toast.makeText(v.context, "Saved!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    v.setBackgroundResource(R.drawable.ic_baseline_bookmark_border_24)
                    CoroutineScope(Dispatchers.IO).launch {
                        LocalConstants.API_CLIENT.deleteTheEntry(token, id)
                    }
                    Toast.makeText(v.context, "Removed!", Toast.LENGTH_SHORT)
                        .show()
                }

            }
        })
    }

    fun getSavedNews(): List<NewsResponse> {
        LocalConstants.API_CLIENT.getAllSavedNews(
            "Bearer " + PreferenceHelper.readStringFromPreference(
                LocalConstants.PREF_TOKEN_VALUE
            )
        ).enqueue(object : Callback<List<NewsResponse>> {
            override fun onFailure(call: Call<List<NewsResponse>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<NewsResponse>>,
                response: Response<List<NewsResponse>>
            ) {

                x.clear()
                val y = response.body()
                if (y != null) {
                    x.addAll(y)
                }
                Log.d("TAG", "onResponse: $x");
                SideNavActivity.newsRvAdapter.notifyDataSetChanged()
            }
        })
        return x;
    }

    fun getTheSavedList() {
        LocalConstants.API_CLIENT.getSavedList(
            "Bearer " +
                    PreferenceHelper.readStringFromPreference(LocalConstants.PREF_TOKEN_VALUE)
            , 0
        ).enqueue(object : Callback<List<SavedEntity>> {
            override fun onFailure(call: Call<List<SavedEntity>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<SavedEntity>>,
                response: Response<List<SavedEntity>>
            ) {
                SideNavActivity.savedListList = (response?.body() as ArrayList<SavedEntity>?)
            }
        })
    }

    fun checkToken(s: String, v: View) {
        LocalConstants.API_CLIENT.isUserValid(s).enqueue(object : Callback<Boolean> {
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Toast.makeText(v.context, "Login Failed", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                try {
                    if (response.body()!!) {
                        val intent = Intent(v.context, SideNavActivity::class.java)
                        v.context.startActivity(intent)
                    } else
                        Toast.makeText(v.context, "Login Failed", Toast.LENGTH_SHORT).show()

                } catch (e: Exception) {
                    Toast.makeText(v.context, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}