package com.ta.ittpizen.data.mapper.connection

import com.ta.ittpizen.data.BuildConfig
import com.ta.ittpizen.data.remote.response.connection.DetailConnectionResponse
import com.ta.ittpizen.domain.model.connection.DetailConnection

fun DetailConnectionResponse.toDomain(): DetailConnection = DetailConnection(
    id = id ?: "",
    photo = (BuildConfig.BASE_URL + "images" + photo),
    name = name ?: "",
    bio = bio ?: "",
    connected = connected ?: false,
    followers = followers ?: 0,
    following = following ?: 0,
    post = post ?: 0,
    type = type ?: ""
)
