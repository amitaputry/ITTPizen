package com.ta.ittpizen.data.remote.response.post

import com.google.gson.annotations.SerializedName

data class CreatePostCommentResponse(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("postId")
	val postId: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("comment")
	val comment: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null
)
