package com.thehindu.themain.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserResponse(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("dob")
	val dob: String? = null,

	@field:SerializedName("address")
	val address: Int? = null,

	@field:SerializedName("phone")
	val phone: Int? = null,

	@field:SerializedName("subscription")
	val subscription: Int? = null,

	@field:SerializedName("created_with_google")
	val createdWithGoogle: Int? = null
)