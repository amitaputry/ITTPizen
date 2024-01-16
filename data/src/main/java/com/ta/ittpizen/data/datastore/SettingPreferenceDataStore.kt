package com.ta.ittpizen.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.datastore: DataStore<Preferences> by preferencesDataStore("setting")

class SettingPreferenceDataStore(private val context: Context) {

    companion object {
        private val FIRST_TIME_OPEN = booleanPreferencesKey("first_time")
        private val IS_LOGIN = booleanPreferencesKey("is_login")
    }

    private val datastore get() = context.datastore

    val isFirstOpen: Flow<Boolean> get() = datastore.data.map {  preferences ->
        preferences[FIRST_TIME_OPEN] ?: true
    }

    val isLogin: Flow<Boolean> get() = datastore.data.map {  preferences ->
        preferences[IS_LOGIN] ?: false
    }

    suspend fun updateFirstOpenState(state: Boolean) {
        datastore.edit { preferences ->
            preferences[FIRST_TIME_OPEN] = state
        }
    }

    suspend fun updateIsLoginState(state: Boolean) {
        datastore.edit { preferences ->
            preferences[IS_LOGIN] = state
        }
    }

}