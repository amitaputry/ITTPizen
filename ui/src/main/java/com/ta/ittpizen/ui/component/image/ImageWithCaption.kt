package com.ta.ittpizen.ui.component.image

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.component.text.TextBodyMedium
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun ImageWithCaption(
    modifier: Modifier = Modifier,
    painter: Painter,
    caption: String,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageWithBorder(
            painter = painter,
            onClick = onClick,
            contentDescription = caption
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextBodyMedium(text = caption)
    }
}

@Preview
@Composable
fun PreviewImageWithCaption() {
    ITTPizenTheme {
        Surface {
            ImageWithCaption(
                painter = painterResource(id = R.drawable.ic_add_tweet),
                caption = "Tweet",
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}
