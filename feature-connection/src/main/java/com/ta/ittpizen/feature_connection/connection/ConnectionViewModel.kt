package com.ta.ittpizen.feature_connection.connection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ta.ittpizen.domain.usecase.IttpizenPagedUseCase
import com.ta.ittpizen.domain.usecase.IttpizenUseCase
import com.ta.ittpizen.domain.usecase.UserPreferenceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ConnectionViewModel(
    private val userPreferenceUseCase: UserPreferenceUseCase,
    private val ittpizenPagedUseCase: IttpizenPagedUseCase,
    private val ittpizenUseCase: IttpizenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ConnectionUiState())
    val uiState: StateFlow<ConnectionUiState> get() = _uiState

    val userPreference get() = userPreferenceUseCase.userPreference

    fun getAllConnection(token: String, type: String) {
        _uiState.update {
            it.copy(
                connectionLoaded = true,
                connections = ittpizenPagedUseCase.getAllConnection(token = token, type = type),
            )
        }
    }

    fun createConnection(
        token: String,
        userId: String
    ) {
        viewModelScope.launch {
            ittpizenUseCase.createConnection(token, userId).collect()
        }
    }

    fun deleteConnection(
        token: String,
        userId: String
    ) {
        viewModelScope.launch {
            ittpizenUseCase.deleteConnection(token, userId).collect()
        }
    }

}