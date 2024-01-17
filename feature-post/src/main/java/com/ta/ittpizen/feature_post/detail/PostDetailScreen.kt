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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.model.post.Post
import com.ta.ittpizen.domain.model.post.PostComment
import com.ta.ittpizen.domain.model.preference.UserPreference
import com.ta.ittpizen.feature_post.component.PostDetailEmptyComment
import com.ta.ittpizen.feature_post.component.PostDetailFooter
import com.ta.ittpizen.ui.component.post.PostCommentItem
import com.ta.ittpizen.ui.component.post.PostItem
import com.ta.ittpizen.ui.component.topappbar.DetailTopAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import org.koin.androidx.compose.koinViewModel

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun PostDetailScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
    viewModel: PostDetailViewModel = koinViewModel(),
    postId: String = ""
) {

    val lazyState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val userPreference by viewModel.userPreference.collectAsStateWithLifecycle(initialValue = UserPreference())
    val buttonEnabled by viewModel.buttonEnabled.collectAsStateWithLifecycle(initialValue = false)

    val post = uiState.post
    var postData by remember { mutableStateOf(Post()) }

    val postComment = uiState.postComment
    val postCommentData = remember { mutableStateListOf<PostComment>() }

    var message by remember { mutableStateOf("") }

    val onSendButtonClick: () -> Unit = {
//        comments.add(commentItem)
//        message = ""
//        scope.launch {
//            lazyState.animateScrollToItem(comments.lastIndex)
//        }
    }

    val onLikeClicked: (Post) -> Unit = {
        val token = userPreference.accessToken
        if (it.liked) {
            viewModel.deletePostLike(token, postId)
        } else {
            viewModel.createPostLike(token, postId)
        }
    }

    LaunchedEffect(key1 = userPreference) {
        if (userPreference.accessToken.isEmpty()) return@LaunchedEffect
        val token = userPreference.accessToken
        viewModel.getPostById(token = token, postId = postId)
        viewModel.getPostComment(token = token, postId = postId)
    }

    LaunchedEffect(key1 = post) {
        if (post is Resource.Success) {
            postData = post.data
        }
    }

    LaunchedEffect(key1 = postComment) {
        if (postComment is Resource.Success) {
            postCommentData.clear()
            postCommentData.addAll(postComment.data)

            if (postCommentData.isEmpty()) return@LaunchedEffect
            lazyState.animateScrollToItem(postCommentData.lastIndex)
        }
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
                enabled = buttonEnabled,
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
                    PostItem(
                        post = postData,
                        enabled = false,
                        onLike = onLikeClicked
                    )
                }
                item { Spacer(modifier = Modifier.height(10.dp)) }
                items(items = postCommentData, key = { it.id }) {
                    PostCommentItem(
                        post = it,
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .padding(vertical = 10.dp)
                    )
                }
            }
            AnimatedVisibility(
                visible = postCommentData.isEmpty(),
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
