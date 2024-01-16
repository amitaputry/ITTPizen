package com.ta.ittpizen.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingPreferenceRepository {

    val isFirstOpen: Flow<Boolean>

    val isLogging: Flow<Boolean>

    suspend fun updateFirstOpenState(state: Boolean)

    suspend fun updateIsLoginState(state: Boolean)

}