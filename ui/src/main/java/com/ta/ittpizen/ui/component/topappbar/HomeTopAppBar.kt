package com.ta.ittpizen.ui.component.topappbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalMaterial3Api
@Composable
fun HomeTopAppBar(
    modifier: Modifier = Modifier,
    profile: String = "",
    onNotificationClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val context = LocalContext.current
    Row(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp, bottom = 10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(painter = painterResource(id = R.drawable.ic_home_ittpizen), contentDescription = "ITTPizen")
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.ic_notification),
            contentDescription = "Notification",
            modifier = Modifier
                .size(26.dp)
                .clip(CircleShape)
                .clickable { onNotificationClick() }
        )
        Spacer(modifier = Modifier.width(10.dp))
        val request = ImageRequest.Builder(context)
            .data(profile)
            .placeholder(R.drawable.ic_profile_default)
            .error(R.drawable.ic_profile_default)
            .crossfade(true)
            .build()
        AsyncImage(
            model = request,
            contentDescription = "Profile",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(26.dp)
                .clip(CircleShape)
                .clickable { onProfileClick() }
        )
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewHomeTopAppBar() {
    ITTPizenTheme {
        Surface {
            HomeTopAppBar()
        }
    }
}
