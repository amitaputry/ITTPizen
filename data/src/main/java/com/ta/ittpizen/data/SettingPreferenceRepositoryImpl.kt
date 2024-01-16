package com.ta.ittpizen.data

import com.ta.ittpizen.data.datastore.SettingPreferenceDataStore
import com.ta.ittpizen.domain.repository.SettingPreferenceRepository
import kotlinx.coroutines.flow.Flow

class SettingPreferenceRepositoryImpl(
    private val datastore: SettingPreferenceDataStore
) : SettingPreferenceRepository {

    override val isFirstOpen: Flow<Boolean>
        get() = datastore.isFirstOpen
    override val isLogging: Flow<Boolean>
        get() = datastore.isLogin

    override suspend fun updateFirstOpenState(state: Boolean) {
        datastore.updateFirstOpenState(state)
    }

    override suspend fun updateIsLoginState(state: Boolean) {
        datastore.updateIsLoginState(state)
    }

}