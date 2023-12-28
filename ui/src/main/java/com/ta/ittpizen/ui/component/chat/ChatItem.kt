package com.ta.ittpizen.ui.component.chat

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ta.ittpizen.domain.model.ChatItem
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.component.text.TextBodyLarge
import com.ta.ittpizen.ui.component.text.TextBodySmall
import com.ta.ittpizen.ui.theme.ColorText
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.SecondDarkGrey

@Composable
fun ChatItem(
    modifier: Modifier = Modifier,
    chat: ChatItem,
    onClick: (ChatItem) -> Unit = {},
) {
    Row(
        modifier = Modifier
            .clickable { onClick(chat) }
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = chat.photo,
            placeholder = painterResource(id = R.drawable.ic_profile_default),
            error = painterResource(id = R.drawable.ic_profile_default),
            contentDescription = "image description",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextBodyLarge(
                    text = chat.name,
                    color = ColorText,
                    fontWeight = FontWeight(500),
                    maxLines = 1
                )
                Spacer(modifier = Modifier.width(4.dp))
                TextBodySmall(
                    text = chat.date,
                    color = SecondDarkGrey
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextBodySmall(
                    text = chat.message,
                    color = SecondDarkGrey,
                    maxLines = 1
                )
                if (chat.unReadChat > 0) {
                    Spacer(modifier = Modifier.width(4.dp))
                    ChatUnreadIndicator(count = chat.unReadChat)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewChatItem() {
    ITTPizenTheme {
        Surface {
            val user = ChatItem(
                name = "Amita Putry Prasasti",
                message = "Yorem ipsum dolor sit amet, consectetur ..",
                date = "6/12/2023",
                unReadChat = 2
            )
            ChatItem(
                chat = user,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            )
        }
    }
}
