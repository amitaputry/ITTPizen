package com.ta.ittpizen.domain.entity

data class ChatItem(
    val id: String = "",
    val photo: String = "",
    val name: String = "",
    val message: String = "",
    val date: String = "",
    val unReadChat: Int = 0
)
