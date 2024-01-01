package com.ta.ittpizen.feature_chat.chat

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.domain.utils.DataChatBubble
import com.ta.ittpizen.domain.utils.DataChatItem
import com.ta.ittpizen.feature_chat.component.EmptyChatContent
import com.ta.ittpizen.ui.component.chat.ChatItem
import com.ta.ittpizen.ui.component.searchbar.FilledSearchBar
import com.ta.ittpizen.ui.component.topappbar.BaseTopAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    navigateToDetailChatScreen: (chatId: String, friendId: String) -> Unit = { _, _ -> }
) {

    var query by remember { mutableStateOf("") }

    val lastChat = DataChatBubble.getLastMessage()
    val chatItems = DataChatItem.getAllChatItems()
        .map {
            it.copy(
                message = lastChat.text
            )
        }

    var chats by remember {
        mutableStateOf(chatItems)
    }

    val onSearch: (String) -> Unit = { search ->
        query = search
        chats = chatItems.filter { it.name.contains(search, true) }
    }

    Scaffold(modifier = modifier) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            item {
                BaseTopAppBar(title = "Chat")
            }
            stickyHeader {
                FilledSearchBar(
                    query = query,
                    onQueryChange = onSearch,
                    placeholder = "Search",
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .fillMaxWidth()
                )
            }
            if (chats.isEmpty()) {
                item {
                    EmptyChatContent(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 100.dp)
                    )
                }
            }
            items(items = chats, key = { it.id }) {
                ChatItem(
                    chat = it,
                    onClick = { navigateToDetailChatScreen(it.id, it.userId) },
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .fillMaxWidth()
                        .animateItemPlacement()
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewChatScreen() {
    ITTPizenTheme {
        Surface {
            ChatScreen()
        }
    }
}
        