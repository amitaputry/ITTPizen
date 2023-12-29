package com.ta.ittpizen.feature_notification.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ta.ittpizen.domain.model.NotificationItem
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.component.iconbutton.BaseIconButton
import com.ta.ittpizen.ui.component.text.TextBodyLarge
import com.ta.ittpizen.ui.component.text.TextBodySmall
import com.ta.ittpizen.ui.theme.ColorText
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.SecondDarkGrey

@Composable
fun NotificationItem(
    modifier: Modifier = Modifier,
    notification: NotificationItem,
    onClick: (NotificationItem) -> Unit = {}
) {
    Row(
        modifier = modifier.padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = notification.photo,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            placeholder = painterResource(id = R.drawable.ic_profile_default),
            error = painterResource(id = R.drawable.ic_profile_default),
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        TextBodyLarge(
            text = notification.name,
            fontWeight = FontWeight.Medium,
            color = ColorText
        )
        Spacer(modifier = Modifier.width(5.dp))
        TextBodySmall(
            text = notification.message,
            color = SecondDarkGrey
        )
        Spacer(modifier = Modifier.weight(1f))
        BaseIconButton(
            icon = painterResource(id = R.drawable.ic_overflow_menu),
            contentDescription = "Menu",
            onClick = { onClick(notification) },
            modifier = Modifier.offset(x = 10.dp)
        )
    }
}

@Preview
@Composable
fun PreviewNotificationItem() {
    ITTPizenTheme {
        Surface {
            val notificationItem = NotificationItem(
                name = "Abdul Hafiz Ramadan",
                message = "Liked your post"
            )
            NotificationItem(
                notification = notificationItem,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}

