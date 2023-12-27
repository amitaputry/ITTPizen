package com.ta.ittpizen.feature_auth.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    private val _registerUiState = MutableStateFlow(LoginUiState())
    val registerUiState: StateFlow<LoginUiState> get() = _registerUiState

    val buttonRegisterEnable get() = _registerUiState.map {
        it.email.isNotEmpty() && it.password.length >= 6
    }

    val emailError get() = _registerUiState.map {
        it.emailErrorMessage.isNotEmpty()
    }

    val passwordError get() = _registerUiState.map {
        it.passwordErrorMessage.isNotEmpty()
    }

    fun updateEmail(email: String) {
        _registerUiState.update {
            it.copy(email = email)
        }
    }

    fun updatePassword(password: String) {
        _registerUiState.update {
            it.copy(password = password)
        }
    }

    fun updateEmailErrorMessage(message: String) {
        _registerUiState.update {
            it.copy(emailErrorMessage = message)
        }
    }

    fun updatePasswordErrorMessage(message: String) {
        _registerUiState.update {
            it.copy(passwordErrorMessage = message)
        }
    }

}