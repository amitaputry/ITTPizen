package com.ta.ittpizen.data

import androidx.datastore.core.DataStore
import com.ta.ittpizen.data.mapper.preference.toDomain
import com.ta.ittpizen.domain.repository.UserPreferenceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.ta.ittpizen.domain.model.preference.UserPreference as UserPreferenceDomain

class UserPreferenceRepositoryImpl(
    private val userPreferenceSetting: DataStore<UserPreference>
) : UserPreferenceRepository {

    override val userPreference: Flow<UserPreferenceDomain>
        get() = userPreferenceSetting.data.map { userPreference ->
            userPreference.toDomain()
        }

    override suspend fun updateUserPreference(userPreference: UserPreferenceDomain) {
        userPreferenceSetting.updateData { preference ->
            preference.toBuilder()
                .setUserId(userPreference.userId)
                .setPhoto(userPreference.photo)
                .setName(userPreference.name)
                .setEmail(userPreference.email)
                .setType(userPreference.type)
                .setAccessToken(userPreference.accessToken)
                .build()
        }
    }

}