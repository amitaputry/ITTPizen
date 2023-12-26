package com.ta.ittpizen.ui.component.iconbutton

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.theme.GreyShadow
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun FilledIconButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    contentDescription: String? = null,
    onClick: () -> Unit = {},
    containerColor: Color = GreyShadow,
    size: Dp = 42.dp
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .size(size)
            .clickable { onClick() }
            .background(containerColor),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = icon,
            contentDescription = contentDescription
        )
    }
}

@Preview
@Composable
fun PreviewFilledIconButton() {
    ITTPizenTheme {
        Surface {
            FilledIconButton(
                icon = painterResource(id = R.drawable.ic_add_primary),
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}
