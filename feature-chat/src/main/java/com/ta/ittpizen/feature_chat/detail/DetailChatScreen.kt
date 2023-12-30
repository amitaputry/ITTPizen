package com.ta.ittpizen.feature_chat.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.common.toChatDateFormat
import com.ta.ittpizen.common.toChatTimeFormat
import com.ta.ittpizen.domain.model.ChatBubble
import com.ta.ittpizen.domain.utils.DataChatBubble
import com.ta.ittpizen.domain.utils.DataChatItem
import com.ta.ittpizen.domain.utils.DataUserItem
import com.ta.ittpizen.feature_chat.R
import com.ta.ittpizen.feature_chat.component.DetailChatFooter
import com.ta.ittpizen.feature_chat.component.EmptyDetailChatContent
import com.ta.ittpizen.ui.component.chat.ChatBubble
import com.ta.ittpizen.ui.component.chat.ChatBubbleDate
import com.ta.ittpizen.ui.component.chat.ChatBubbleType
import com.ta.ittpizen.ui.component.topappbar.ChatTopAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Composable
fun DetailChatScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
    chatId: String = "",
    friendId: String = "",
) {

    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    val localDateTime = LocalDateTime.now()

    var message by remember { mutableStateOf("") }

    val userId = "0"

    val friend = DataUserItem.getUserById(friendId) ?: return

    val chats = remember { mutableStateListOf<ChatBubble>() }

    val groupingChats = chats.groupBy { it.date }

    val onSendButtonClick: () -> Unit = {
        val date = localDateTime.toChatDateFormat()
        val time = localDateTime.toChatTimeFormat()
        val chatItem = ChatBubble(
            id = UUID.randomUUID().toString(),
            userId = userId,
            date = date,
            time = time,
            text = message
        )
        chats.add(chatItem)
        message = ""
        scope.launch {
            scrollState.animateScrollToItem(chats.lastIndex)
        }
    }

    LaunchedEffect(key1 = Unit) {
        val chatBubbles = DataChatBubble.getChatBubbleByChatId(chatId = chatId)
        chats.clear()
        chats.addAll(chatBubbles)
    }

    LaunchedEffect(key1 = Unit) {
        if (chats.isEmpty()) return@LaunchedEffect
        scrollState.animateScrollToItem(chats.lastIndex)
    }

    Scaffold(
        topBar = {
            ChatTopAppBar(
                onNavigateClick = navigateUp,
                name = friend.name,
                type = friend.type
            )
        },
        bottomBar = {
            DetailChatFooter(
                value = message,
                onValueChange = { message = it },
                onSendClick = onSendButtonClick,
                placeholder = "Write the message"
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_chat),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            AnimatedVisibility(
                visible = chats.isEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                EmptyDetailChatContent(
                    date = localDateTime.toChatDateFormat()
                )
            }
            LazyColumn(
                state = scrollState,
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                groupingChats.forEach { (date, chats) ->
                    stickyHeader {
                        ChatBubbleDate(date = date)
                    }
                    items(items = chats, key = { it.id }) { chat ->
                        val type = if (chat.userId == userId) ChatBubbleType.ME else ChatBubbleType.FRIEND
                        ChatBubble(
                            message = chat.text,
                            time = chat.time,
                            type = type,
                            modifier = Modifier.animateItemPlacement()
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewDetailChatScreen() {
    ITTPizenTheme {
        Surface {
            DetailChatScreen()
        }
    }
}
