package com.ta.ittpizen.data.mapper.profile

import com.ta.ittpizen.data.BuildConfig
import com.ta.ittpizen.data.remote.response.profile.ProfileResponse
import com.ta.ittpizen.domain.model.profile.Profile

fun ProfileResponse.toDomain(): Profile {
    val photo = photo ?: ""
    val photoUrl = if (photo.isNotEmpty()) BuildConfig.BASE_URL + "images" + photo else ""
    return Profile(
        id = id ?: "",
        name = name ?: "",
        photo = photoUrl,
        bio = bio ?: "",
        type = type ?: ""
    )
}
