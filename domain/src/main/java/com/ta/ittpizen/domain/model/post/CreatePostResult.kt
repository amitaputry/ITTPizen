package com.ta.ittpizen.domain.model.post

data class CreatePostResult(

	val id: String = "",

	val userId: String = "",

	val text: String = "",

	val media: String = "",

	val type: String = "",

	val liked: Boolean = false,

	val createdAt: String = ""
)
