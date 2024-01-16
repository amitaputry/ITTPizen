package com.ta.ittpizen.data.remote.response.post

import com.google.gson.annotations.SerializedName

data class UpdatePostResponse(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("media")
	val media: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("liked")
	val liked: Boolean? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null
)
