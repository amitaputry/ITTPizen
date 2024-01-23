package com.ta.ittpizen.ui.component.user

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ta.ittpizen.domain.model.connection.Connection
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.component.button.SmallPrimaryButton
import com.ta.ittpizen.ui.component.button.SmallPrimaryOutlinedButton
import com.ta.ittpizen.ui.component.text.TextBodyLarge
import com.ta.ittpizen.ui.component.text.TextBodySmall
import com.ta.ittpizen.ui.theme.ColorText
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.SecondDarkGrey

@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    user: Connection,
    onClick: (Connection) -> Unit = {},
    onConnectClick: (Connection) -> Unit = {},
) {
    var connected by rememberSaveable(key = user.connected.toString()) { mutableStateOf(user.connected) }
    val onConnectClicked: (Connection) -> Unit = {
        onConnectClick(user.copy(connected = connected))
        connected = connected.not()
    }
    Row(
        modifier = Modifier
            .clickable { onClick(user) }
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = user.photo,
            placeholder = painterResource(id = R.drawable.ic_profile_default),
            error = painterResource(id = R.drawable.ic_profile_default),
            contentDescription = "image description",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            TextBodyLarge(
                text = user.name,
                color = ColorText,
                fontWeight = FontWeight(500),
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(2.dp))
            TextBodySmall(
                text = user.type,
                color = SecondDarkGrey
            )
        }
        if (connected) {
            SmallPrimaryOutlinedButton(text = "Connected", onClick = { onConnectClicked(user) })
        } else {
            SmallPrimaryButton(text = "Connect", onClick = { onConnectClicked(user) })
        }
    }
}

@Preview
@Composable
fun PreviewUserItem() {
    ITTPizenTheme {
        Surface {
            val user = Connection(
                name = "Amita Putry Prasasti",
                type = "Student"
            )
            UserItem(
                user = user,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            )
        }
    }
}
