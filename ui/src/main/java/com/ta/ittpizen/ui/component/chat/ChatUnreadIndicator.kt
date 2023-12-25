package com.ta.ittpizen.ui.component.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.component.text.TextBodySmall
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.PrimaryRed

@Composable
fun ChatUnreadIndicator(
    modifier: Modifier = Modifier,
    count: Int = 0
) {
    TextBodySmall(
        text = count.toString(),
        color = Color.White,
        modifier = modifier
            .clip(CircleShape)
            .background(PrimaryRed)
            .padding(horizontal = 6.dp, vertical = 2.dp),
    )
}

@Preview
@Composable
fun PreviewChatUnreadIndicator() {
    ITTPizenTheme {
        Surface {
            ChatUnreadIndicator(
                modifier = Modifier.padding(16.dp),
                count = 2
            )
        }
    }
}
