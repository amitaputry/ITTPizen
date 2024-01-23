package com.ta.ittpizen.feature_home

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.ta.ittpizen.domain.model.post.Post
import com.ta.ittpizen.domain.model.post.PostType
import com.ta.ittpizen.domain.model.preference.UserPreference
import com.ta.ittpizen.ui.component.content.EmptyContent
import com.ta.ittpizen.ui.component.post.PostItem
import com.ta.ittpizen.ui.component.tab.BaseScrollableTabRow
import com.ta.ittpizen.ui.component.topappbar.HomeTopAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.PrimaryRed
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@ExperimentalFoundationApi
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    navigateToMyProfileScreen: (String) -> Unit = {},
    navigateToUserProfileScreen: (String) -> Unit = {},
    navigateToNotificationScreen: (String) -> Unit = {},
    navigateToDetailPostScreen: (String) -> Unit = {},
    navigateToPhotoDetailScreen: (String) -> Unit = {},
) {

    val tabs = listOf("All Post", "Tweet", "Academic", "#PrestasiITTP", "Events", "Scholarship")

    val context = LocalContext.current
    val pagerState = rememberPagerState { tabs.size }
    val scope = rememberCoroutineScope()

    var showProgressLoading by remember { mutableStateOf(true) }

    val userPreference by viewModel.userPreference.collectAsStateWithLifecycle(initialValue = UserPreference())
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val allPostLoaded = uiState.allPostLoaded
    val allPost = uiState.allPost.collectAsLazyPagingItems()

    val selectedTabIndex by remember(key1 = pagerState.currentPage) {
        mutableIntStateOf(pagerState.currentPage)
    }

    val postType by remember(key1 = pagerState.currentPage) {
        val type = when (pagerState.currentPage) {
            1 -> PostType.Tweet
            2 -> PostType.Academic
            3 -> PostType.Achievement
            4 -> PostType.Event
            5 -> PostType.Scholarship
            else -> PostType.All
        }
        mutableStateOf(type)
    }

    val onTabSelected: (Int) -> Unit = {
        scope.launch {
            pagerState.scrollToPage(it)
        }
    }

    val onProfileClicked: (Post) -> Unit = { post ->
        showProgressLoading = false
        val userId = post.user.id
        val isMe = userPreference.userId == userId
        if (isMe) {
            navigateToMyProfileScreen(userId)
        } else {
            navigateToUserProfileScreen(userId)
        }
    }

    val onLikeClicked: (Post) -> Unit = { post ->
        if (post.liked) {
            viewModel.deletePostLike(token = userPreference.accessToken, postId = post.id)
        } else {
            viewModel.createPostLike(token = userPreference.accessToken, postId = post.id)
        }
    }

    val onShareClicked: (Post) -> Unit = { post ->
        val text = buildString {
            append(post.text)
            append("\n\n")
            append("By ITTPizen")
        }
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }

    LaunchedEffect(key1 = userPreference) {
        if (userPreference.accessToken.isEmpty()) return@LaunchedEffect
        if (allPostLoaded) return@LaunchedEffect
        viewModel.getAllPost(token = userPreference.accessToken)
    }

    LaunchedEffect(key1 = postType) {
        if (userPreference.accessToken.isEmpty()) return@LaunchedEffect
        viewModel.getAllPost(token = userPreference.accessToken, type = postType)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            HomeTopAppBar(
                onProfileClick = { navigateToMyProfileScreen(userPreference.userId) },
                onNotificationClick = { navigateToNotificationScreen(userPreference.userId) },
                profile = userPreference.photo
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            BaseScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                tabs = tabs,
                onSelected = onTabSelected,
                edgePadding = 20.dp,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) {
                if (allPost.loadState.refresh is LoadState.Loading) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator(color = PrimaryRed)
                    }
                }
                if (allPost.itemCount == 0 && allPost.loadState.refresh is LoadState.NotLoading) {
                    EmptyContent(
                        title = "There is no post :(",
                        modifier = Modifier.padding(top = 130.dp)
                    )
                }
                LazyColumn {
                    items(count = allPost.itemCount, key = { allPost[it]?.id ?: it }) {
                        val post = allPost[it]
                        if (post != null) {
                            PostItem(
                                post = post,
                                onProfileClick = onProfileClicked,
                                onClick = { navigateToDetailPostScreen(it.id) },
                                onPhotoClick = { navigateToPhotoDetailScreen(it) },
                                onLike = { onLikeClicked(it) },
                                onComment = { navigateToDetailPostScreen(post.id) },
                                onSend = onShareClicked,
                                modifier = Modifier.padding(top = 20.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalLayoutApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewHomeScreen() {
    ITTPizenTheme {
        Surface {
            HomeScreen()
        }
    }
}
