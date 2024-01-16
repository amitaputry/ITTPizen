package com.ta.ittpizen.data.mapper.preference

import com.ta.ittpizen.domain.model.preference.UserPreference
import com.ta.ittpizen.data.UserPreference as UserPreferenceDataStore

fun UserPreferenceDataStore.toDomain(): UserPreference = UserPreference(
    userId = userId,
    photo = photo,
    name = name,
    type = type,
    email = email,
    accessToken = accessToken
)
