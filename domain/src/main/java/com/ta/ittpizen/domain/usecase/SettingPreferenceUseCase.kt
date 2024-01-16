package com.ta.ittpizen.domain.usecase

import kotlinx.coroutines.flow.Flow

interface SettingPreferenceUseCase {

    val isFirstOpen: Flow<Boolean>

    val isLogging: Flow<Boolean>

    suspend fun updateFirstOpenState(state: Boolean)

    suspend fun updateIsLoginState(state: Boolean)

}