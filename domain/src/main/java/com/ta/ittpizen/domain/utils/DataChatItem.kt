package com.ta.ittpizen.domain.utils

import com.ta.ittpizen.domain.model.ChatItem

object DataChatItem {

    private  val allChatItems = mutableListOf(
        ChatItem(
            id = "1",
            userId = "3",
            name = "Afifatunni’mah",
            date = "6/12/2023"
        ),
        ChatItem(
            id = "2",
            userId = "2",
            name = "Abdul Hafiz Ramadan",
            date = "1/12/2023"
        ),
        ChatItem(
            id = "3",
            userId = "9",
            name = "Yolanda Al Hidayah Pasaribu",
            date = "3/12/2023"
        )
    )

    fun getAllChatItems(): List<ChatItem> = allChatItems

    fun getChatByFriendId(id: String): ChatItem? = allChatItems
        .find { it.id == id }

}