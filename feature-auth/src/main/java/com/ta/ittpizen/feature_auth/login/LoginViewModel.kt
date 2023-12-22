package com.ta.ittpizen.feature_auth.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    private val _registerUiState = MutableStateFlow(LoginUiState())
    val registerUiState: StateFlow<LoginUiState> get() = _registerUiState

    private var firstStart = true

    val buttonRegisterEnable get() = _registerUiState.map {
        it.email.isNotEmpty() && it.password.length >= 6
    }

    fun updateEmail(email: String) {
        _registerUiState.update {
            it.copy(email = email)
        }
    }

    fun updatePassword(password: String) {
        firstStart = false
        _registerUiState.update {
            it.copy(password = password)
        }
    }
}