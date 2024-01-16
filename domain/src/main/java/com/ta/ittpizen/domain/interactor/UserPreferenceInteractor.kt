package com.ta.ittpizen.domain.interactor

import com.ta.ittpizen.domain.model.preference.UserPreference
import com.ta.ittpizen.domain.repository.UserPreferenceRepository
import com.ta.ittpizen.domain.usecase.UserPreferenceUseCase
import kotlinx.coroutines.flow.Flow

class UserPreferenceInteractor(private val repository: UserPreferenceRepository) : UserPreferenceUseCase {
    override val userPreference: Flow<UserPreference>
        get() = repository.userPreference

    override suspend fun updateUserPreference(userPreference: UserPreference) {
        repository.updateUserPreference(userPreference)
    }
}