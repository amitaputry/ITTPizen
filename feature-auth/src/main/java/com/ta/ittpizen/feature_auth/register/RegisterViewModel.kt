package com.ta.ittpizen.feature_auth.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {

    private val _registerUiState = MutableStateFlow(RegisterUiState())
    val registerUiState: StateFlow<RegisterUiState> get() = _registerUiState

    val buttonRegisterEnable = _registerUiState.map {
        it.fullName.isNotEmpty() && it.email.isNotEmpty() && it.gender.isNotEmpty() &&
        it.status.isNotEmpty() && it.password.length >= 6 && it.password == it.confirmPassword
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
}