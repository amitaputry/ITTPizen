package com.ta.ittpizen.domain.model.auth

data class LoginResult(
	val id: String = "",
	val photo: String = "",
	val name: String = "",
	val type: String = "",
	val email: String = "",
	val accessToken: String = "",
	val refreshToken: String = ""
)
