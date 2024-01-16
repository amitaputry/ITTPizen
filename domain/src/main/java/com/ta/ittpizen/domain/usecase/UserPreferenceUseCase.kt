package com.ta.ittpizen.domain.usecase

import com.ta.ittpizen.domain.model.UserPreference
import kotlinx.coroutines.flow.Flow

interface UserPreferenceUseCase {

    val userPreference: Flow<UserPreference>

    suspend fun updateUserPreference(userPreference: UserPreference)

    suspend fun updateAccessToken(accessToken: String)

}