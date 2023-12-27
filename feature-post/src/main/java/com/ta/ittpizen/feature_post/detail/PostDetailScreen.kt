package com.ta.ittpizen.feature_post.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.domain.entity.PostCommentItem
import com.ta.ittpizen.domain.entity.PostItem
import com.ta.ittpizen.feature_post.component.PostDetailEmptyComment
import com.ta.ittpizen.feature_post.component.PostDetailFooter
import com.ta.ittpizen.ui.component.post.PostCommentItem
import com.ta.ittpizen.ui.component.post.PostItem
import com.ta.ittpizen.ui.component.topappbar.DetailTopAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import kotlinx.coroutines.launch
import java.util.UUID

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun PostDetailScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
    postId: String = ""
) {

    val lazyState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var message by remember { mutableStateOf("") }

    val userId = "user123"

    val postItem = PostItem(
        name = "Amita Putry Prasasti",
        type = "Student",
        date = "1 hours ago",
        profile = "",
        text = "Haloo, salam kenal, mari saling koneksi temen-temen. Haloo, salam kenal, mari saling koneksi temen-temen. Haloo, salam kenal, mari saling koneksi temen-temen. Haloo, salam kenal, mari saling koneksi temen-temen",
//        media = "https://imgv2-2-f.scribdassets.com/img/document/435811139/original/93f2b7f3b2/1696470516?v=1",
        liked = true
    )

    val comments = remember { mutableStateListOf<PostCommentItem>() }

    val onSendButtonClick: () -> Unit = {
        val commentItem = PostCommentItem(
            id = UUID.randomUUID().toString(),
            userId = userId,
            name = "Abdul Hafiz Ramadan",
            type = "Student",
            date = "1 hours ago",
            text = message
        )
        comments.add(commentItem)
        message = ""
        scope.launch {
            lazyState.animateScrollToItem(comments.lastIndex)
        }
    }

    LaunchedEffect(key1 = Unit) {
        if (comments.isEmpty()) return@LaunchedEffect
        lazyState.animateScrollToItem(comments.lastIndex)
    }

    Scaffold(
        topBar = {
            DetailTopAppBar(
                onNavigationClick = navigateUp
            )
        },
        bottomBar = {
            PostDetailFooter(
                value = message,
                onValueChange = { message = it },
                placeholder = "Add a comment...",
                onSendClick = onSendButtonClick,
                modifier = Modifier.fillMaxWidth()
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            LazyColumn(
                state = lazyState,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                item {
                    PostItem(
                        post = postItem,
                        enabled = false,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }
                items(items = comments, key = { it.id }) {
                    PostCommentItem(
                        post = it,
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
//                            .animateItemPlacement()
                    )
                }
            }
            AnimatedVisibility(
                visible = comments.isEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                PostDetailEmptyComment(modifier = Modifier.weight(1f))
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewPostDetailScreen() {
    ITTPizenTheme {
        Surface {
            PostDetailScreen()
        }
    }
}
