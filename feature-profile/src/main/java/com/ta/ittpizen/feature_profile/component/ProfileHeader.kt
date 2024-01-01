package com.ta.ittpizen.feature_profile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ta.ittpizen.domain.model.Profile
import com.ta.ittpizen.feature_profile.R
import com.ta.ittpizen.ui.component.iconbutton.BaseIconButton
import com.ta.ittpizen.ui.component.text.TextBodyLarge
import com.ta.ittpizen.ui.component.text.TextBodyMedium
import com.ta.ittpizen.ui.component.text.TextBodySmall
import com.ta.ittpizen.ui.theme.ColorText
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.SecondDarkGrey

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
    onLogoutClicked: () -> Unit = {},
    profile: Profile,
    showLogOutButton: Boolean = false
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier) {
            ProfileHeaderBackground(
                navigateUp = navigateUp,
                showLogOutButton = showLogOutButton,
                onLogoutClicked = onLogoutClicked
            )
            ProfileHeaderContent(
                profile = profile
            )
        }
        AsyncImage(
            model = profile.photo,
            contentDescription = profile.name,
            contentScale = ContentScale.FillBounds,
            placeholder = painterResource(id = R.drawable.ic_profile_default),
            error = painterResource(id = R.drawable.ic_profile_default),
            modifier = Modifier
                .offset(y = 118.dp)
                .clip(CircleShape)
                .size(75.dp)
                .align(Alignment.TopCenter)
        )
    }
}

@Composable
fun ProfileHeaderBackground(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
    showLogOutButton: Boolean = false,
    onLogoutClicked: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .height(156.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_profile),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 20.dp)
                .fillMaxSize()
        ) {
            BaseIconButton(
                icon = painterResource(id = com.ta.ittpizen.ui.R.drawable.ic_back),
                contentDescription = "Back",
                onClick = navigateUp,
                modifier = Modifier.offset(x = (-4).dp, y = (-4).dp)
            )
            TextBodyMedium(
                text = "#SosmednyaWargaITTP",
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Let’s connect and share your stories with ITTPizen!",
                fontSize = 10.sp,
                color = Color.White,
                letterSpacing = 0.04.sp,
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_globe),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = "https://ittelkom-pwt.ac.id/",
                    fontSize = 6.sp,
                    fontWeight = FontWeight(500),
                    color = Color.White,
                    letterSpacing = 0.02.sp
                )
            }
        }
        if (!showLogOutButton) {
            Image(
                painter = painterResource(id = R.drawable.logo_ittp),
                contentDescription = null,
                modifier = Modifier
                    .size(15.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = (-10).dp, y = (24).dp)
            )
        }
        if (showLogOutButton) {
            LogoutButton(
                onClick = onLogoutClicked,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = (-10).dp, y = (20).dp)
            )
        }
    }
}

@Composable
fun ProfileHeaderContent(
    modifier: Modifier = Modifier,
    profile: Profile
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(52.dp))
        TextBodyLarge(
            text = profile.name,
            fontWeight = FontWeight.Medium,
            color = ColorText,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(2.dp))
        TextBodyMedium(
            text = profile.type,
            color = SecondDarkGrey,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(14.dp))
        TextBodySmall(
            text = profile.bio,
            color = SecondDarkGrey,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            UserDataInfo(
                data = profile.post,
                label = "Post"
            )
            Spacer(modifier = Modifier.width(20.dp))
            UserDataInfo(
                data = profile.followers,
                label = "Followers"
            )
            Spacer(modifier = Modifier.width(20.dp))
            UserDataInfo(
                data = profile.following,
                label = "Following"
            )
        }
    }
}

@Composable
fun UserDataInfo(
    modifier: Modifier = Modifier,
    data: Int = 0,
    label: String = ""
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextBodyLarge(
            text = data.toString(),
            fontWeight = FontWeight.Medium,
            color = ColorText
        )
        TextBodySmall(
            text = label,
            color = ColorText
        )
    }
}

@Preview
@Composable
fun PreviewProfileHeader() {
    ITTPizenTheme {
        Surface {
            val profile = Profile(
                name = "Amita Putry Prasasti",
                type = "Student",
                bio = "I am Software Engineering Student at Telkom Institute of Technology Purwokerto IG @amt_p3",
                post = 1,
                followers = 100,
                following = 10
            )
            ProfileHeader(
                profile = profile,
            )
        }
    }
}
