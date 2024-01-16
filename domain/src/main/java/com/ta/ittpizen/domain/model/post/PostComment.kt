package com.ta.ittpizen.domain.model.post

data class PostComment(

	val id: String = "",

	val postId: String = "",

	val comment: String = "",

	val user: PostUser = PostUser(),

	val createdAt: String = ""
)
