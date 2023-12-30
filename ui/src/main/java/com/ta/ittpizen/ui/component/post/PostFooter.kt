package com.ta.ittpizen.ui.component.post

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.component.iconbutton.BaseIconButton
import com.ta.ittpizen.ui.component.text.TextBodySmall
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.SecondDarkGrey

@Composable
fun PostFooter(
    modifier: Modifier = Modifier,
    liked: Boolean = false,
    like: Int = 0,
    comment: Int = 0,
    onLike: () -> Unit = {},
    onComment: () -> Unit = {},
    onSend: () -> Unit = {}
) {
    val iconLike = if (liked) R.drawable.ic_liked else R.drawable.ic_like
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            TextBodySmall(text = "$like Likes", color = SecondDarkGrey)
            Image(
                painter = painterResource(id = R.drawable.ic_circle),
                contentDescription = null
            )
            TextBodySmall(text = "$comment comments", color = SecondDarkGrey)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.offset(x = (-4).dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            BaseIconButton(
                icon = painterResource(id = iconLike),
                contentDescription = "Like",
                onClick = onLike
            )
            BaseIconButton(
                icon = painterResource(id = R.drawable.ic_comment),
                contentDescription = "Comment",
                onClick = onComment
            )
            BaseIconButton(
                icon = painterResource(id = R.drawable.ic_share),
                contentDescription = "Send",
                onClick = onSend
            )
        }
    }
}

@Preview
@Composable
fun PreviewPostFooter() {
    ITTPizenTheme {
        Surface {
            PostFooter(
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
