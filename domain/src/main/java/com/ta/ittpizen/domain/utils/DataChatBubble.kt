package com.ta.ittpizen.domain.utils

import com.ta.ittpizen.domain.model.ChatBubble

object DataChatBubble {

    private val allChatBubbles = mutableListOf(
        ChatBubble(
            id = "1",
            chatId = "1",
            userId = "3",
            date = "06 December 2023",
            time = "21:59",
            text = "Halo Amita, Apa Kabar?"
        ),
        ChatBubble(
            id = "2",
            chatId = "1",
            userId = "0",
            date = "06 December 2023",
            time = "21:59",
            text = "Hi, Baik, Ada apa ya?"
        ),
        ChatBubble(
            id = "3",
            chatId = "1",
            userId = "3",
            date = "06 December 2023",
            time = "21:59",
            text = "Agar silahturahmi tidak putus, boleh pinjem seratus ga mita? :)"
        ),
        ChatBubble(
            id = "4",
            chatId = "1",
            userId = "0",
            date = "06 December 2023",
            time = "22:01",
            text = "\uD83D\uDE4F\uD83D\uDE4F\uD83D\uDE4F\uD83D\uDE4F\uD83D\uDE4F"
        ),
        ChatBubble(
            id = "5",
            chatId = "1",
            userId = "3",
            date = "06 December 2023",
            time = "22:02",
            text = "Wah makasih Amita"
        ),
        ChatBubble(
            id = "6",
            chatId = "2",
            userId = "3",
            date = "06 December 2023",
            time = "21:59",
            text = "Halo apa Kabar?"
        ),
        ChatBubble(
            id = "7",
            chatId = "2",
            userId = "0",
            date = "06 December 2023",
            time = "21:59",
            text = "Hi, Baik, Ada apa ya?"
        ),
        ChatBubble(
            id = "8",
            chatId = "3",
            userId = "0",
            date = "06 December 2023",
            time = "21:59",
            text = "Hi kak yolanda, apa kabar?"
        ),
        ChatBubble(
            id = "8",
            chatId = "3",
            userId = "9",
            date = "06 December 2023",
            time = "21:59",
            text = "Hi kabar baik nihh"
        ),
    )

    fun getLastMessage(): ChatBubble = allChatBubbles.last()

    fun getChatBubbleByChatId(chatId: String): List<ChatBubble> = allChatBubbles.filter {
        it.chatId == chatId
    }

}