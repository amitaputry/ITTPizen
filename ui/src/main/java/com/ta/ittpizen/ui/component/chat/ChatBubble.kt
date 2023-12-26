package com.ta.ittpizen.ui.component.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.component.text.TextBodyMedium
import com.ta.ittpizen.ui.component.text.TextBodySmall
import com.ta.ittpizen.ui.theme.ColorText
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.SecondDarkGrey

enum class ChatBubbleType {
    ME, FRIEND
}

@Composable
fun ChatBubble(
    modifier: Modifier = Modifier,
    message: String,
    time: String,
    type: ChatBubbleType = ChatBubbleType.ME
) {
    when (type) {
        ChatBubbleType.ME -> ChatBubbleMe(
            modifier, message, time
        )
        ChatBubbleType.FRIEND -> ChatBubbleFriend(
            modifier, message, time
        )
    }
}

@Composable
private fun ChatBubbleMe(
    modifier: Modifier = Modifier,
    message: String,
    time: String,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        TextBodyMedium(
            text = message,
            color = ColorText,
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 16.dp))
                .wrapContentWidth()
                .background(Color.White)
                .padding(horizontal = 20.dp, vertical = 10.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        TextBodySmall(
            text = time,
            color = SecondDarkGrey
        )
    }
}

@Composable
private fun ChatBubbleFriend(
    modifier: Modifier = Modifier,
    message: String,
    time: String,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        TextBodyMedium(
            text = message,
            color = ColorText,
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomEnd = 16.dp))
                .wrapContentWidth()
                .background(Color(0xFFFFCDCA))
                .padding(horizontal = 20.dp, vertical = 10.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        TextBodySmall(
            text = time,
            color = SecondDarkGrey
        )
    }
}


@Preview
@Composable
fun PreviewChatBubble() {
    ITTPizenTheme {
        Surface {
            ChatBubble(
                message = "06 December 2023",
                time = "21:59",
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewChatBubbleMe() {
    ITTPizenTheme {
        Surface {
            ChatBubbleMe(
                message = "Halo Amita, Apa Kabar?",
                time = "21:59",
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewChatBubbleFriend() {
    ITTPizenTheme {
        Surface {
            ChatBubbleFriend(
                message = "Halo Amita, Apa Kabar?",
                time = "21:59",
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}

