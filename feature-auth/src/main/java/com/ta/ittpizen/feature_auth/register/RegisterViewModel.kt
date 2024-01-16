package com.ta.ittpizen.feature_auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.model.auth.RegisterResult
import com.ta.ittpizen.domain.usecase.IttpizenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val useCase: IttpizenUseCase
) : ViewModel() {

    private val _registerUiState = MutableStateFlow(RegisterUiState())
    val registerUiState: StateFlow<RegisterUiState> get() = _registerUiState

    private val _registerResult = MutableStateFlow<Resource<RegisterResult>>(Resource.Idle)
    val registerResult: StateFlow<Resource<RegisterResult>> get() = _registerResult

    val buttonRegisterEnabled = _registerUiState.map {
        it.fullName.isNotEmpty() && it.email.isNotEmpty() && it.gender.isNotEmpty() &&
        it.status.isNotEmpty() && it.password.length >= 6 && it.password == it.confirmPassword
    }

    val buttonRegisterLoading = _registerResult.map {
        it is Resource.Loading
    }

    val fullNameError get() = _registerUiState.map {
        it.fullNameErrorMessage.isNotEmpty()
    }
    val emailError get() = _registerUiState.map {
        it.emailErrorMessage.isNotEmpty()
    }
    val passwordError get() = _registerUiState.map {
        it.passwordErrorMessage.isNotEmpty()
    }
    val confirmPasswordError get() = _registerUiState.map {
        it.confirmPasswordErrorMessage.isNotEmpty()
    }

    fun updateFullName(fullName: String) {
        _registerUiState.update {
            it.copy(fullName = fullName)
        }
    }

    fun updateStudentIdOrLectureId(studentIdOrLectureId: String) {
        _registerUiState.update {
            it.copy(studentIdOrLectureId = studentIdOrLectureId)
        }
    }

    fun updateEmail(email: String) {
        _registerUiState.update {
            it.copy(email = email)
        }
    }
    fun updatePhone(phone: String) {
        _registerUiState.update {
            it.copy(phone = phone)
        }
    }
    fun updateGender(gender: String) {
        _registerUiState.update {
            it.copy(gender = gender)
        }
    }
    fun updateStatus(status: String) {
        _registerUiState.update {
            it.copy(status = status)
        }
    }
    fun updatePassword(password: String) {
        _registerUiState.update {
            it.copy(password = password)
        }
    }
    fun updateConfirmPassword(confirmPassword: String) {
        _registerUiState.update {
            it.copy(confirmPassword = confirmPassword)
        }
    }

    fun updateFullNameErrorMessage(message: String) {
        _registerUiState.update {
            it.copy(fullNameErrorMessage = message)
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
    fun updateConfirmPasswordErrorMessage(message: String) {
        _registerUiState.update {
            it.copy(confirmPasswordErrorMessage = message)
        }
    }

    fun register() {
        val name = _registerUiState.value.fullName
        val studentOrLectureId = _registerUiState.value.studentIdOrLectureId
        val email = _registerUiState.value.email
        val phone = _registerUiState.value.phone
        val gender = _registerUiState.value.gender
        val status = _registerUiState.value.status
        val password = _registerUiState.value.password
        viewModelScope.launch {
            useCase.register(name, studentOrLectureId, email, phone, gender, status, password).collect { result ->
                _registerResult.update { result }
            }
        }
    }

}