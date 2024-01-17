package com.ta.ittpizen.data.mapper.post

import com.ta.ittpizen.data.remote.response.post.PostCommentResponse
import com.ta.ittpizen.domain.model.post.PostComment
import com.ta.ittpizen.domain.model.post.PostUser

fun List<PostCommentResponse>.toDomains(): List<PostComment> = map {
    it.toDomain()
}

fun PostCommentResponse.toDomain(): PostComment = PostComment(
    id = id ?: "",
    postId = postId ?: "",
    user = user?.toDomain() ?: PostUser(),
    comment = comment ?: "",
    createdAt = createdAt ?: ""
)
