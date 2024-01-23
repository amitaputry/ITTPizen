package com.ta.ittpizen.data.remote.response.profile

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("bio")
	val bio: String? = null,

	@field:SerializedName("type")
	val type: String? = null
)
