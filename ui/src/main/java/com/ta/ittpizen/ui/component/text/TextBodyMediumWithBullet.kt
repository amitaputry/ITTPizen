package com.ta.ittpizen.ui.component.text

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun TextBodyMediumWithBullet(
    modifier: Modifier = Modifier,
    bullet: Painter,
    text: String,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = MaterialTheme.colorScheme.onBackground,
    size: Dp = 4.dp
) {
    Row(modifier = modifier) {
        Image(
            painter = bullet,
            contentDescription = null,
            modifier = Modifier
                .padding(6.dp)
                .size(size)
        )
        TextBodyMedium(
            text = text,
            fontWeight = fontWeight,
            color = color
        )
    }
}

@Preview
@Composable
fun PreviewTextBodySmallWithBullet() {
    ITTPizenTheme {
        Surface {
            TextBodyMediumWithBullet(
                bullet = painterResource(id = R.drawable.ic_ellipse),
                text = "PT Graha Indonesia",
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}
