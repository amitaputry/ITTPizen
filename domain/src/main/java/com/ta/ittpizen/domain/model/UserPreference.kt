package com.ta.ittpizen.domain.model

data class UserPreference(
    val userId: String = "",
    val name: String = "",
    val type: String = "",
    val email: String = "",
    val accessToken: String = "",
)
