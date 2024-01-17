package com.ta.ittpizen.data.remote.response.job

import com.google.gson.annotations.SerializedName

data class SaveJobResponse(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("jobId")
	val jobId: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null
)
