package com.ta.ittpizen.feature_profile.profile

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.maxkeppeker.sheets.core.CoreDialog
import com.maxkeppeker.sheets.core.models.CoreSelection
import com.maxkeppeker.sheets.core.models.base.SelectionButton
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.model.connection.DetailConnection
import com.ta.ittpizen.domain.model.post.Post
import com.ta.ittpizen.domain.model.preference.UserPreference
import com.ta.ittpizen.feature_profile.component.ProfileFriendButtonSection
import com.ta.ittpizen.feature_profile.component.ProfileHeader
import com.ta.ittpizen.feature_profile.component.ProfileMeButtonSection
import com.ta.ittpizen.feature_profile.component.ProfilePostIndicator
import com.ta.ittpizen.ui.component.content.EmptyContent
import com.ta.ittpizen.ui.component.post.PostItem
import com.ta.ittpizen.ui.component.text.TextBodyMedium
import com.ta.ittpizen.ui.component.text.TextTitleSmall
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.PrimaryRed
import com.ta.ittpizen.ui.theme.SecondDarkGrey
import org.koin.androidx.compose.koinViewModel

enum class ProfileScreenType {
    ME,
    FRIEND
}

@ExperimentalMaterial3Api
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
    val logoutDialogState = rememberUseCaseState()
    val messageDialogState = rememberUseCaseState()

    var dialogTitle by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf("") }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val userPreference by viewModel.userPreference.collectAsStateWithLifecycle(initialValue = UserPreference())

    val profile = uiState.profile
    val allPostLoaded = uiState.allPostLoaded
    val allPost = uiState.allPost.collectAsLazyPagingItems()

    var profileData by remember { mutableStateOf(DetailConnection()) }

    val logoutButtonVisible = remember {
        type == ProfileScreenType.ME
    }

    val primaryText by remember(key1 = profileData) {
        val text = if (profileData.connected) "Connected" else "Connect"
        mutableStateOf(text)
    }
    val secondaryText = "Message"

    val onLogoutClick: () -> Unit = {
        dialogTitle = "Warning message!"
        dialogMessage = "Are you sure you want to log out?"
        logoutDialogState.show()
    }

    val onMessageClick: () -> Unit = {
        if (profileData.connected.not()) {
            dialogTitle = "Warning message!"
            dialogMessage = "You have to connect this user before can send a message?"
            messageDialogState.show()
        } else {
            navigateToDetailChatScreen("-", userId)
        }
    }

    val logout: () -> Unit = {
        viewModel.logout()
        navigateToLoginScreen()
    }

    val onConnectClick: () -> Unit = {
        val token = userPreference.accessToken
        if (profileData.connected) {
            viewModel.deleteConnection(token, userId)
        } else {
            viewModel.createConnection(token, userId)
        }
        val updatedFollower = if (profileData.connected) profileData.followers - 1 else profileData.followers + 1
        profileData = profileData.copy(
            connected = profileData.connected.not(),
            followers = updatedFollower
        )
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

        val token = userPreference.accessToken
        viewModel.getProfile(token, userId)
        viewModel.getAllPost(token, userId)
    }

    LaunchedEffect(key1 = profile) {
        if (profile is Resource.Success) {
            profileData = profile.data
        }
    }

    CoreDialog(
        state = logoutDialogState,
        body = {
            TextTitleSmall(text = dialogTitle)
            Spacer(modifier = Modifier.height(16.dp))
            TextBodyMedium(text = dialogMessage, color = SecondDarkGrey)
        },
        selection = CoreSelection(
            positiveButton = SelectionButton(text = "Logout"),
            onPositiveClick = logout
        )
    )

    CoreDialog(
        state = messageDialogState,
        body = {
            TextTitleSmall(text = dialogTitle)
            Spacer(modifier = Modifier.height(16.dp))
            TextBodyMedium(text = dialogMessage, color = SecondDarkGrey)
        },
        selection = CoreSelection(
            positiveButton = SelectionButton(text = "Ok")
        )
    )

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
                    onLogoutClicked = onLogoutClick,
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
            if (allPost.itemCount == 0 && allPost.loadState.refresh is LoadState.NotLoading) {
                item {
                    EmptyContent(
                        title = "There is post :(",
                        modifier = Modifier.padding(top = 10.dp)
                    )
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
                            onPhotoClick = navigateToDetailPhotoScreen,
                            modifier = Modifier.padding(top = 20.dp)
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
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
