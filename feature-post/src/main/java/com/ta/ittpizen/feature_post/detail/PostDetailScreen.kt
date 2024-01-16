package com.ta.ittpizen.feature_post.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.ta.ittpizen.domain.model.PostCommentItem
import com.ta.ittpizen.domain.model.PostItem
import com.ta.ittpizen.domain.utils.DataPostItem
import com.ta.ittpizen.feature_post.component.PostDetailEmptyComment
import com.ta.ittpizen.feature_post.component.PostDetailFooter
import com.ta.ittpizen.ui.component.post.PostCommentItem
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

    var postItem by remember {
        mutableStateOf(DataPostItem.getById(postId))
    }

    if (postItem == null) return

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

    val onLikeClicked: (PostItem) -> Unit = { post ->
        postItem = DataPostItem.likeOrDislikePost(post)
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
            LazyColumn(state = lazyState) {
                item {
//                    PostItem(
//                        post = postItem!!,
//                        enabled = false,
//                        onLike = onLikeClicked
//                    )
                }
                item { Spacer(modifier = Modifier.height(10.dp)) }
                items(items = comments, key = { it.id }) {
                    PostCommentItem(
                        post = it,
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .padding(vertical = 10.dp)
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
