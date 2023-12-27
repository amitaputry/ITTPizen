package com.ta.ittpizen.feature_auth.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val emailErrorMessage: String = "",
    val passwordErrorMessage: String = "",
)
