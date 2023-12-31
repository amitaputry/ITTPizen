package com.ta.ittpizen.feature_profile.profile

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.domain.model.PostItem
import com.ta.ittpizen.domain.utils.DataPostItem
import com.ta.ittpizen.domain.utils.DataProfile
import com.ta.ittpizen.domain.utils.DataUserItem
import com.ta.ittpizen.feature_profile.component.ProfileFriendButtonSection
import com.ta.ittpizen.feature_profile.component.ProfileHeader
import com.ta.ittpizen.feature_profile.component.ProfileMeButtonSection
import com.ta.ittpizen.feature_profile.component.ProfilePostIndicator
import com.ta.ittpizen.ui.component.post.PostItem
import com.ta.ittpizen.ui.theme.ITTPizenTheme

enum class ProfileScreenType {
    ME,
    FRIEND
}

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    userId: String = "",
    type: ProfileScreenType = ProfileScreenType.ME,
    navigateUp: () -> Unit = {},
    navigateToLoginScreen: () -> Unit = {},
    navigateToEditProfile: () -> Unit = {},
    navigateToSavedJob: () -> Unit = {},
    navigateToDetailPostScreen: (String) -> Unit = {},
    navigateToDetailChatScreen: (chatId: String, friendId: String) -> Unit = { _, _ -> },
) {

    var profile by remember {
        val user = DataUserItem.getUserById(id = userId)
        DataProfile.updateConnectState(user!!.id, user.connected)
        val profile = DataProfile.getProfileById(userId)
        mutableStateOf(profile)
    }

    if (profile == null) return

    val logoutButtonVisible = remember {
        type == ProfileScreenType.ME
    }

    val primaryText by remember(key1 = profile) {
        val text = if (profile!!.connected) "Connected" else "Connect"
        mutableStateOf(text)
    }
    val secondaryText = "Message"

    val postItems = remember { mutableStateListOf<PostItem>() }

    val onConnectClick: () -> Unit = {
        val updatedProfile = DataProfile.connectToProfile(profile!!)
        profile = updatedProfile
    }

    val onMessageClick: () -> Unit = {
        navigateToDetailChatScreen("-", userId)
    }

    val onLikeClicked: (PostItem) -> Unit = { post ->
        val updatedPost = DataPostItem.likeOrDislikePost(post)!!
        postItems.replaceAll {
            if (it.id == updatedPost.id) updatedPost else it
        }
    }

    LaunchedEffect(key1 = Unit) {
        postItems.addAll(DataPostItem.getByUserId(userId))
    }

    Scaffold(modifier = modifier) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { 
                ProfileHeader(
                    profile = profile!!,
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
            items(items = postItems, key = { it.id }) {
                PostItem(
                    post = it,
                    onClick = { navigateToDetailPostScreen(it.id) },
                    onLike = onLikeClicked,
                    onComment = { navigateToDetailPostScreen(it.id) }
                )
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
