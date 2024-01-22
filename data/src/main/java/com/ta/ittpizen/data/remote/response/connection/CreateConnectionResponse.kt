package com.ta.ittpizen.data.remote.response.connection

import com.google.gson.annotations.SerializedName

data class CreateConnectionResponse(

	@field:SerializedName("connected")
	val connected: Boolean? = null

)
