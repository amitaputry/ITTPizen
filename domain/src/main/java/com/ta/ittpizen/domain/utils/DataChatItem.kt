package com.ta.ittpizen.domain.utils

import com.ta.ittpizen.domain.model.ChatItem

object DataChatItem {

    private  val allChatItems = mutableListOf(
        ChatItem(
            id = "1",
            userId = "3",
            name = "Afifatunni’mah",
            date = "6/12/2023"
        )
    )

    fun getAllChatItems(): List<ChatItem> = allChatItems

    fun getChatByFriendId(id: String): ChatItem? = allChatItems
        .find { it.id == id }

}