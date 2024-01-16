package com.ta.ittpizen.feature_auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.model.auth.LoginResult
import com.ta.ittpizen.domain.model.preference.UserPreference
import com.ta.ittpizen.domain.usecase.IttpizenUseCase
import com.ta.ittpizen.domain.usecase.SettingPreferenceUseCase
import com.ta.ittpizen.domain.usecase.UserPreferenceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val useCase: IttpizenUseCase,
    private val settingUseCase: SettingPreferenceUseCase,
    private val userPreferenceUseCase: UserPreferenceUseCase
) : ViewModel() {

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> get() = _loginUiState

    private val _loginResult = MutableStateFlow<Resource<LoginResult>>(Resource.Idle)
    val loginResult: StateFlow<Resource<LoginResult>> get() = _loginResult

    val buttonLoadingLoading get() = _loginResult.map {
        it is Resource.Loading || it is Resource.Success
    }

    val buttonLoginEnabled get() = _loginUiState.map {
        it.email.isNotEmpty() && it.password.length >= 6
    }

    val emailError get() = _loginUiState.map {
        it.emailErrorMessage.isNotEmpty()
    }

    val passwordError get() = _loginUiState.map {
        it.passwordErrorMessage.isNotEmpty()
    }

    fun updateEmail(email: String) {
        _loginUiState.update {
            it.copy(email = email)
        }
    }

    fun updatePassword(password: String) {
        _loginUiState.update {
            it.copy(password = password)
        }
    }

    fun updateEmailErrorMessage(message: String) {
        _loginUiState.update {
            it.copy(emailErrorMessage = message)
        }
    }

    fun updatePasswordErrorMessage(message: String) {
        _loginUiState.update {
            it.copy(passwordErrorMessage = message)
        }
    }

    fun login() {
        val email = _loginUiState.value.email
        val password = _loginUiState.value.password
        viewModelScope.launch {
            useCase.login(email, password).collect { result ->
                _loginResult.update { result }
            }
        }
    }

    fun updateIsLoginState(state: Boolean) {
        viewModelScope.launch {
            settingUseCase.updateIsLoginState(state)
        }
    }

    fun updateUserPreference(userPreference: UserPreference) {
        viewModelScope.launch {
            userPreferenceUseCase.updateUserPreference(userPreference)
        }
    }

}