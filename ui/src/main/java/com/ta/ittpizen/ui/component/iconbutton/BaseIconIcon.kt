package com.ta.ittpizen.ui.component.iconbutton

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun BaseIconButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    contentDescription: String? = null,
    onClick: () -> Unit = {}
) {
    Image(
        painter = icon,
        contentDescription = contentDescription,
        modifier = modifier
            .clip(CircleShape)
            .clickable { onClick() }
            .padding(4.dp)
            .size(24.dp)
    )
}

@Preview
@Composable
fun PreviewBaseIconButton() {
    ITTPizenTheme {
        Surface {
            BaseIconButton(
                icon = painterResource(id = R.drawable.ic_like),
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}
