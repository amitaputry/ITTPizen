package com.ta.ittpizen.data.remote.response.job

import com.google.gson.annotations.SerializedName

data class DetailJobResponse(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("company")
	val company: String? = null,

	@field:SerializedName("street")
	val street: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("province")
	val province: String? = null,

	@field:SerializedName("workplaceType")
	val workplaceType: String? = null,

	@field:SerializedName("jobType")
	val jobType: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("skills")
	val skills: List<String>? = null,

	@field:SerializedName("experience")
	val experience: String? = null,

	@field:SerializedName("graduates")
	val graduates: String? = null,

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("saved")
	val saved: Boolean? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null

)
