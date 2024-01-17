package com.ta.ittpizen.data.remote.response.connection

import com.google.gson.annotations.SerializedName

data class DetailConnectionResponse(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("bio")
	val bio: String? = null,

	@field:SerializedName("connected")
	val connected: Boolean? = null,

	@field:SerializedName("followers")
	val followers: Int? = null,

	@field:SerializedName("post")
	val post: Int? = null,

	@field:SerializedName("following")
	val following: Int? = null,

	@field:SerializedName("type")
	val type: String? = null
)
