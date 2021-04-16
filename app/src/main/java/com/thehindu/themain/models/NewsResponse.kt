package com.thehindu.themain.models

import com.google.gson.annotations.SerializedName

data class NewsResponse(

	@field:SerializedName("category_id")
	val categoryId: Int? = null,

	@field:SerializedName("sub_heading")
	val subHeading: String? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("posted_at")
	val postedAt: String? = null,

	@field:SerializedName("multi_media")
	val multiMedia: String? = null,

	@field:SerializedName("headline")
	val headline: String? = null,

	@field:SerializedName("content")
	val content: String? = null
)