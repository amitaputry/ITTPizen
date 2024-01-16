package com.ta.ittpizen.data.remote.request.auth

import com.google.gson.annotations.SerializedName

data class RegisterRequest(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("studentOrLectureId")
	val studentOrLectureId: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("gender")
	val gender: String,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("password")
	val password: String

)
