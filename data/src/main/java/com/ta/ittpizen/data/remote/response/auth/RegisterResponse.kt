package com.ta.ittpizen.data.remote.response.auth

import com.google.gson.annotations.SerializedName
data class RegisterResponse(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("studentOrLectureId")
	val studentOrLectureId: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("bio")
	val bio: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

)
