package com.ta.ittpizen.domain.model.connection

data class DetailConnection(

	val id: String = "",

	val photo: String = "",

	val name: String = "",

	val bio: String = "",

	val connected: Boolean = false,

	val followers: Int = 0,

	val post: Int = 0,

	val following: Int = 0,

	val type: String = ""

)
