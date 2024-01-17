package com.ta.ittpizen.data.remote.response.connection

import com.google.gson.annotations.SerializedName

data class ConnectionResponse(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("connected")
	val connected: Boolean? = null

)
