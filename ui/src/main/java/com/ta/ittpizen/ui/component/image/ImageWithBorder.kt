package com.ta.ittpizen.ui.component.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.theme.DisableColorGrey
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun ImageWithBorder(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String? = null,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(size = 20.dp))
            .border(
                width = 1.dp,
                color = DisableColorGrey,
                shape = RoundedCornerShape(size = 20.dp)
            )
            .size(85.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier.size(55.dp)
        )
    }
}

@Preview
@Composable
fun PreviewImageWithBorder() {
    ITTPizenTheme {
        Surface {
            ImageWithBorder(
                painter = painterResource(id = R.drawable.ic_add_tweet)
            )
        }
    }
}
