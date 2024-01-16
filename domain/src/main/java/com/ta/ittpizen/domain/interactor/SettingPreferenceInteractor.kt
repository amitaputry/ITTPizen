package com.ta.ittpizen.domain.interactor

import com.ta.ittpizen.domain.repository.SettingPreferenceRepository
import com.ta.ittpizen.domain.usecase.SettingPreferenceUseCase
import kotlinx.coroutines.flow.Flow

class SettingPreferenceInteractor(private val repository: SettingPreferenceRepository) :
    SettingPreferenceUseCase {

    override val isFirstOpen: Flow<Boolean>
        get() = repository.isFirstOpen
    override val isLogging: Flow<Boolean>
        get() = repository.isLogging

    override suspend fun updateFirstOpenState(state: Boolean) {
        repository.updateFirstOpenState(state)
    }

    override suspend fun updateIsLoginState(state: Boolean) {
        repository.updateIsLoginState(state)
    }

}