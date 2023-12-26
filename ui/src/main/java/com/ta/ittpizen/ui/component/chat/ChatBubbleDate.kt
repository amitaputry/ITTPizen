package com.ta.ittpizen.ui.component.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.component.text.TextBodySmall
import com.ta.ittpizen.ui.theme.ColorText
import com.ta.ittpizen.ui.theme.GreyShadow
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun ChatBubbleDate(
    modifier: Modifier = Modifier,
    date: String
) {
    TextBodySmall(
        text = date,
        color = ColorText,
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .background(GreyShadow)
            .padding(horizontal = 20.dp, vertical = 10.dp)
    )
}

@Preview
@Composable
fun PreviewChatBubbleDate() {
    ITTPizenTheme {
        Surface {
            ChatBubbleDate(
                date = "06 December 2023",
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}
