package com.ta.ittpizen.data.mapper.post

import com.ta.ittpizen.data.BuildConfig
import com.ta.ittpizen.data.remote.response.post.PostResponse
import com.ta.ittpizen.domain.model.post.Post
import com.ta.ittpizen.domain.model.post.PostUser

fun List<PostResponse>.toDomains(): List<Post> = map {
    it.toDomain()
}

fun PostResponse.toDomain(): Post {
    val media = media ?: ""
    val mediaUrl = if (media.isNotEmpty()) BuildConfig.BASE_URL + "images" + media else ""
    return Post(
        id = id ?: "",
        user = user?.toDomain() ?: PostUser(),
        text = text ?: "",
        media = mediaUrl,
        type = type ?: "",
        liked = liked ?: false,
        likes = likes ?: 0,
        comments = comments ?: 0,
        createdAt = createdAt ?: ""
    )
}
