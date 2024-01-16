package com.ta.ittpizen.data.mapper.auth

import com.ta.ittpizen.data.remote.response.auth.LoginResponse
import com.ta.ittpizen.domain.model.auth.LoginResult

fun LoginResponse.toDomain(): LoginResult = LoginResult(
    id = id ?: "",
    photo = photo ?: "",
    name = name ?: "",
    type = type ?: "",
    email = email ?: "",
    accessToken = accessToken ?: "",
    refreshToken = refreshToken ?: ""
)
