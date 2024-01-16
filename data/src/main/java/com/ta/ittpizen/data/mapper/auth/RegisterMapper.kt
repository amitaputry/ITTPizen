package com.ta.ittpizen.data.mapper.auth

import com.ta.ittpizen.data.remote.response.auth.RegisterResponse
import com.ta.ittpizen.domain.model.auth.RegisterResult

fun RegisterResponse.toDomain(): RegisterResult = RegisterResult(
    id = id ?: "",
    name = name ?: "",
    email = email ?: "",
    studentOrLectureId = studentOrLectureId ?: "",
    gender = gender ?: "",
    phone = phone ?: "",
    status = status ?: "",
    photo = photo ?: "",
    bio = bio ?: "",
    createdAt = createdAt ?: ""
)
