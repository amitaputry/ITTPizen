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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.domain.model.PostItem
import com.ta.ittpizen.domain.model.Profile
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
    navigateToEditProfile: () -> Unit = {},
    navigateToSavedJob: (String) -> Unit = {},
    navigateToDetailPostScreen: (String) -> Unit = {}
) {

    val profile = Profile(
        name = "Amita Putry Prasasti",
        type = "Student",
        bio = "I am Software Engineering Student at Telkom Institute of Technology Purwokerto IG @amt_p3",
        post = 1,
        followers = 100,
        following = 10
    )

    val postItems = (1..10).map {
        val media = when (it) {
            3 -> "https://static.promediateknologi.id/crop/0x0:0x0/750x500/webp/photo/ayosemarang/images/post/articles/2021/04/14/75145/kuliah-gratis-institut-teknologi-telkom-purwokerto.jpg"
            7 -> "https://cdn.rri.co.id/berita/47/images/1694653138068-W/1694653138068-W.jpeg"
            else -> ""
        }
        PostItem(
            id = it.toString(),
            name = "Amita Putry Prasasti",
            type = "Student",
            date = "1 hours ago",
            profile = "",
            text = "Haloo, salam kenal, mari saling koneksi temen-temen. Haloo, salam kenal, mari saling koneksi temen-temen. Haloo, salam kenal, mari saling koneksi temen-temen. Haloo, salam kenal, mari saling koneksi temen-temen",
            media = media,
            liked = true
        )
    }

    val primaryText = "Connected"
    val secondaryText = "Message"

    Scaffold(
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { 
                ProfileHeader(
                    profile = profile,
                    navigateUp = navigateUp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            if (type == ProfileScreenType.ME) {
                item {
                    ProfileMeButtonSection(
                        onEditProfileClick = navigateToEditProfile,
                        onSavedJobClick = { navigateToSavedJob(userId) },
                        modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 30.dp)
                    )
                }
            }
            if (type == ProfileScreenType.FRIEND) {
                item {
                    ProfileFriendButtonSection(
                        primaryText = primaryText,
                        secondaryText = secondaryText,
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
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .padding(horizontal = 20.dp)
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
