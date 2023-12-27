package com.ta.ittpizen.feature_auth.register

data class RegisterUiState(
    val fullName: String = "",
    val studentIdOrLectureId: String = "",
    val email: String = "",
    val phone: String = "",
    val gender: String = "",
    val status: String = "",
    val password: String = "",
    val confirmPassword: String = "",

    val fullNameErrorMessage: String = "",
    val studentIdOrLectureIdErrorMessage: String = "",
    val emailErrorMessage: String = "",
    val phoneErrorMessage: String = "",
    val genderErrorMessage: String = "",
    val statusErrorMessage: String = "",
    val passwordErrorMessage: String = "",
    val confirmPasswordErrorMessage: String = "",
)
