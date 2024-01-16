package com.ta.ittpizen.domain.model.post

data class Post(

	val id: String = "",

	val user: PostUser = PostUser(),

	val text: String = "",

	val media: String = "",

	val type: String = "",

	val liked: Boolean = false,

	val likes: Int = 0,

	val comments: Int = 0,

	val createdAt: String = ""
)
