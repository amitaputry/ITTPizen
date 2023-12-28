package com.ta.ittpizen.domain.model

data class Profile(
    val id: String = "",
    val photo: String = "",
    val name: String = "",
    val type: String = "",
    val bio: String = "",
    val post: Int = 0,
    val followers: Int = 0,
    val following: Int = 0,
    val connected: Boolean = false,
)