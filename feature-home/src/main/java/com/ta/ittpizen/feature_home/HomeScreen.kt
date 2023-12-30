package com.ta.ittpizen.feature_home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.domain.model.PostItem
import com.ta.ittpizen.domain.model.PostItemType
import com.ta.ittpizen.domain.utils.DataPostItem
import com.ta.ittpizen.ui.component.post.PostItem
import com.ta.ittpizen.ui.component.tab.BaseScrollableTabRow
import com.ta.ittpizen.ui.component.topappbar.HomeTopAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToMyProfileScreen: (String) -> Unit = {},
    navigateToUserProfileScreen: (String) -> Unit = {},
    navigateToNotificationScreen: (String) -> Unit = {},
    navigateToDetailPostScreen: (String) -> Unit = {},
    navigateToPhotoDetailScreen: (String) -> Unit = {},
) {

    val userId = "0"

    val tabs = listOf("All Post", "Tweet", "Academic", "#PrestasiITTP", "Events", "Scholarship")

    val pagerState = rememberPagerState { tabs.size }
    val scope = rememberCoroutineScope()

    val selectedTabIndex by remember(key1 = pagerState.currentPage) {
        mutableIntStateOf(pagerState.currentPage)
    }

    val postItems = remember { mutableStateListOf<PostItem>() }

    val onTabSelected: (Int) -> Unit = {
        scope.launch {
            pagerState.scrollToPage(it)
        }
    }

    val getPostByType: (type: PostItemType) -> List<PostItem>  = { type ->
        postItems.filter { it.postType == type }
    }

    val onLikeClicked: (PostItem) -> Unit = { post ->
        val updatedPost = DataPostItem.likeOrDislikePost(post)!!
        postItems.replaceAll {
            if (it.id == updatedPost.id) updatedPost else it
        }
    }

    LaunchedEffect(key1 = Unit) {
        postItems.addAll(DataPostItem.generateAllPost())
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            HomeTopAppBar(
                onProfileClick = { navigateToMyProfileScreen(userId) },
                onNotificationClick = { navigateToNotificationScreen(userId) }
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
            ) { page ->
                val items = when (page) {
                    0 -> postItems
                    1 -> getPostByType(PostItemType.TWEET)
                    2 -> getPostByType(PostItemType.ACADEMIC)
                    3 -> getPostByType(PostItemType.ACHIEVEMENT)
                    4 -> getPostByType(PostItemType.EVENT)
                    else -> getPostByType(PostItemType.SCHOLARSHIP)
                }
                LazyColumn {
                    items(items = items, key = { post -> post.id }) { post ->
                        PostItem(
                            post = post,
                            onProfileClick = { navigateToUserProfileScreen(it.userId) },
                            onClick = { navigateToDetailPostScreen(it.id) },
                            onPhotoClick = { navigateToPhotoDetailScreen(it) },
                            onLike = { onLikeClicked(post) },
                            onComment = { navigateToDetailPostScreen(post.id) },
                            modifier = Modifier.padding(top = 20.dp)
                        )
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
