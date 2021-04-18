package com.thehindu.themain.remote

import androidx.annotation.CheckResult
import com.thehindu.themain.localdatabase.savedlist.SavedEntity
import com.thehindu.themain.models.NewsResponse
import com.thehindu.themain.models.UserResponse
import com.thehindu.themain.models.tokenreqres.JwtRequest
import com.thehindu.themain.models.tokenreqres.JwtResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiClient {

    @POST("/login")
    @CheckResult
    fun hitApi(@Body reqModel: JwtRequest): Call<JwtResponse>

    @PUT("/sign_up/{name}/{pass}/{email}")
    suspend fun sign_up(
        @Path("name") name: String,
        @Path("pass") pass: String,
        @Path("email") email: String
    )

    @GET("/allnews")
    fun getAllNews(@Header("Authorization") bearerToken: String): Call<List<NewsResponse>>

    @GET("/userpresent/{email}/{password}")
    fun isUserPresent(
        @Path("email") email: String,
        @Path("password") password: String
    ): Call<Boolean>

    @GET("/getnewsbyid/{id}")
    fun getByNewsId(
        @Header("Authorization") bearerToken: String,
        @Path("id") id: Int
    ): Call<NewsResponse>

    @GET("/isnewspresent/{id}")
    fun isNewsPresent(
        @Header("Authorization") bearerToken: String,
        @Path("id") id: Int?
    ): Call<Boolean>

    @GET("/getSavedList/{id}")
    fun getSavedList(
        @Header("Authorization") bearerToken: String,
        @Path("id") id: Int?
    ): Call<List<SavedEntity>>

    @GET("/newsbycategory/{id}/{str}")
    fun getSpecificNews(
        @Header("Authorization") bearerToken: String,
        @Path("id") id: Int?,
        @Path("str") str: String?
    ): Call<List<NewsResponse>>

    @GET("/getallsavednews")
    fun getAllSavedNews(
        @Header("Authorization") bearerToken: String
    ): Call<List<NewsResponse>>

    @DELETE("/delete_entry/{id}")
    suspend fun deleteTheEntry(
        @Header("Authorization") bearerToken: String,
        @Path("id") id: Int?
    )

    @GET("/isuservalid")
    fun isUserValid(@Header("Authorization") bearerToken: String): Call<Boolean>

    @GET("/getuser")
    fun getUser(@Header("Authorization") bearerToken: String): Call<UserResponse>

    @PATCH("/updateuser/{a}/{b}/{c}")
    suspend fun updateUser(
        @Header("Authorization") bearerToken: String,
        @Path("a") name: String,
        @Path("b") mobile: String,
        @Path("c") dob: String
    )
}