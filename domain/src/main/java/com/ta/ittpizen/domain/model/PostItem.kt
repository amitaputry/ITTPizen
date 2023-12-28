package com.ta.ittpizen.domain.model

data class PostItem(
    val id: String = "",
    val profile: String = "",
    val type: String = "",
    val name: String = "",
    val date: String = "",
    val text: String = "",
    val media: String = "",
    val like: Int = 0,
    val comment: Int = 0,
    val liked: Boolean = false
)
