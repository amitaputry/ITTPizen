package com.ta.ittpizen.domain.model

data class ChatItem(
    val id: String = "",
    val userId: String = "",
    val photo: String = "",
    val name: String = "",
    val message: String = "",
    val date: String = "",
    val unReadChat: Int = 0
)
