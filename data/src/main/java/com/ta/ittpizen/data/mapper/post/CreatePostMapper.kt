package com.ta.ittpizen.data.mapper.post

import com.ta.ittpizen.data.BuildConfig
import com.ta.ittpizen.data.remote.response.post.CreatePostResponse
import com.ta.ittpizen.domain.model.post.CreatePostResult

fun CreatePostResponse.toDomain(): CreatePostResult {
    val media = media ?: ""
    val mediaUrl = if (media.isNotEmpty()) BuildConfig.BASE_URL + "images" + media else ""
    return CreatePostResult(
        id = id ?: "",
        userId = userId ?: "",
        text = text ?: "",
        media = mediaUrl,
        type = type ?: "",
        liked = liked ?: false,
        createdAt = createdAt ?: ""
    )
}
