package com.thehindu.themain.models.tokenreqres

import com.google.gson.annotations.SerializedName

data class JwtRequest(

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("password")
	val password: String? = null
)