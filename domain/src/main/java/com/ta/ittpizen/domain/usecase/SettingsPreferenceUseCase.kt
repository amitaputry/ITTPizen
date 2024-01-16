package com.ta.ittpizen.domain.usecase

import kotlinx.coroutines.flow.Flow

interface SettingsPreferenceUseCase {

    val isLogging: Flow<Boolean>

    suspend fun setLoggingState(state: Boolean)

}