package com.ta.ittpizen.domain.repository

import com.ta.ittpizen.domain.model.preference.UserPreference
import kotlinx.coroutines.flow.Flow

interface UserPreferenceRepository {

    val userPreference: Flow<UserPreference>

    suspend fun updateUserPreference(userPreference: UserPreference)

}