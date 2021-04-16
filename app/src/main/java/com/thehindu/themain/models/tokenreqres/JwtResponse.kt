package com.thehindu.themain.models.tokenreqres

import com.google.gson.annotations.SerializedName

data class JwtResponse(

	@field:SerializedName("token")
	val token: String? = null
)