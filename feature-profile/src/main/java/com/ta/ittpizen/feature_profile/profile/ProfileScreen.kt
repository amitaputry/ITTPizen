package com.ta.ittpizen.feature_profile.profile

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.model.connection.DetailConnection
import com.ta.ittpizen.domain.model.post.Post
import com.ta.ittpizen.domain.model.preference.UserPreference
import com.ta.ittpizen.feature_profile.component.ProfileFriendButtonSection
import com.ta.ittpizen.feature_profile.component.ProfileHeader
import com.ta.ittpizen.feature_profile.component.ProfileMeButtonSection
import com.ta.ittpizen.feature_profile.component.ProfilePostIndicator
import com.ta.ittpizen.ui.component.post.PostItem
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.PrimaryRed
import org.koin.androidx.compose.koinViewModel

enum class ProfileScreenType {
    ME,
    FRIEND
}

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel(),
    userId: String = "",
    type: ProfileScreenType = ProfileScreenType.ME,
    navigateUp: () -> Unit = {},
    navigateToLoginScreen: () -> Unit = {},
    navigateToEditProfile: () -> Unit = {},
    navigateToSavedJob: () -> Unit = {},
    navigateToDetailPostScreen: (String) -> Unit = {},
    navigateToDetailPhotoScreen: (String) -> Unit = {},
    navigateToDetailChatScreen: (chatId: String, friendId: String) -> Unit = { _, _ -> },
) {

    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val userPreference by viewModel.userPreference.collectAsStateWithLifecycle(initialValue = UserPreference())

    val profile = uiState.profile
    val allPostLoaded = uiState.allPostLoaded
    val allPost = uiState.allPost.collectAsLazyPagingItems()

    var profileData by remember { mutableStateOf(DetailConnection()) }

    val logoutButtonVisible = remember {
        type == ProfileScreenType.ME
    }

    val primaryText by remember(key1 = profile) {
        val text = if (profileData.connected) "Connected" else "Connect"
        mutableStateOf(text)
    }
    val secondaryText = "Message"

    val onConnectClick: () -> Unit = {
//        val updatedProfile = DataProfile.connectToProfile(profileData)
//        profile = updatedProfile
    }

    val onMessageClick: () -> Unit = {
        navigateToDetailChatScreen("-", userId)
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
            this.type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }

    LaunchedEffect(key1 = userPreference) {
        if (userPreference.accessToken.isEmpty()) return@LaunchedEffect
        if (allPostLoaded) return@LaunchedEffect

        val token = userPreference.accessToken
        viewModel.getProfile(token, userId)
        viewModel.getAllPost(token, userId)
    }

    LaunchedEffect(key1 = profile) {
        if (profile is Resource.Success) {
            profileData = profile.data
        }
    }

    Scaffold(modifier = modifier) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                ProfileHeader(
                    profile = profileData,
                    navigateUp = navigateUp,
                    showLogOutButton = logoutButtonVisible,
                    onLogoutClicked = navigateToLoginScreen,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            if (type == ProfileScreenType.ME) {
                item {
                    ProfileMeButtonSection(
                        onEditProfileClick = navigateToEditProfile,
                        onSavedJobClick = navigateToSavedJob,
                        modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 30.dp)
                    )
                }
            }
            if (type == ProfileScreenType.FRIEND) {
                item {
                    ProfileFriendButtonSection(
                        primaryText = primaryText,
                        secondaryText = secondaryText,
                        onPrimaryClick = onConnectClick,
                        onSecondaryClick = onMessageClick,
                        modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 30.dp)
                    )
                }
            }
            item {
                ProfilePostIndicator()
            }
            item { Spacer(modifier = Modifier.height(30.dp)) }
            if (allPost.loadState.refresh is LoadState.Loading) {
                item {
                    Box(
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = PrimaryRed)
                    }
                }
            }
            if (allPost.loadState.refresh is LoadState.NotLoading) {
                items(count = allPost.itemCount) {
                    val post = allPost[it]
                    if (post != null) {
                        PostItem(
                            post = post,
                            onClick = { navigateToDetailPostScreen(it.id) },
                            onLike = onLikeClicked,
                            onComment = { navigateToDetailPostScreen(it.id) },
                            onSend = onShareClicked,
                            onPhotoClick = navigateToDetailPhotoScreen
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewProfileScreen() {
    ITTPizenTheme {
        Surface {
            ProfileScreen(
                type = ProfileScreenType.FRIEND
            )
        }
    }
}
