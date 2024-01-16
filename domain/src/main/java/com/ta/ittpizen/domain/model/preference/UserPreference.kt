package com.ta.ittpizen.domain.model.preference

data class UserPreference(
    val userId: String = "",
    val photo: String = "",
    val name: String = "",
    val type: String = "",
    val email: String = "",
    val accessToken: String = "",
)
