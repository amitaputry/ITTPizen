package com.ta.ittpizen.data.mapper.post

import com.ta.ittpizen.data.BuildConfig
import com.ta.ittpizen.data.remote.response.post.PostUserResponse
import com.ta.ittpizen.domain.model.post.PostUser

fun PostUserResponse.toDomain(): PostUser = PostUser(
    id = id ?: "",
    photo = BuildConfig.BASE_URL + "images" + photo,
    name = name ?: "",
    email = email ?: "",
    type = type ?: ""
)
