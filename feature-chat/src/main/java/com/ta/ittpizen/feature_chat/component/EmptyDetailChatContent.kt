package com.ta.ittpizen.feature_chat.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.feature_chat.R
import com.ta.ittpizen.ui.component.chat.ChatBubbleDate
import com.ta.ittpizen.ui.component.text.TextBodyMedium
import com.ta.ittpizen.ui.theme.ColorText
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun EmptyDetailChatContent(
    modifier: Modifier = Modifier,
    date: String = "",
) {
    Box(modifier = modifier.fillMaxSize()) {
        ChatBubbleDate(
            date = date,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 25.dp)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_send_black),
                contentDescription = null
            )
            TextBodyMedium(
                text = "Start messaging now!",
                color = ColorText
            )
        }
    }
}

@Preview
@Composable
fun PreviewEmptyDetailChatContent() {
    ITTPizenTheme {
        Surface {
            EmptyDetailChatContent(
                date = "06 December 2023"
            )
        }
    }
}
