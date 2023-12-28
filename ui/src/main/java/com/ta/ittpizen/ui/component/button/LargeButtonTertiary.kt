package com.ta.ittpizen.ui.component.button

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.component.text.TextBodyMedium
import com.ta.ittpizen.ui.theme.ColorStroke
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun LargeButtonTertiary(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {},
    enable: Boolean = true,
    loading: Boolean = false,
    contentPaddingValues: PaddingValues = PaddingValues(vertical = 13.dp, horizontal = 28.dp)
) {
    val buttonText = if (loading) "Loading..." else text
    val buttonEnabled = if (loading) false else enable
    val buttonBorderColor = ColorStroke
    val textButton = Color(0xB2333538)
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(size = 100.dp))
            .clickable(buttonEnabled) { onClick() }
            .border(
                width = 1.dp,
                color = buttonBorderColor,
                shape = RoundedCornerShape(size = 100.dp)
            )
            .padding(contentPaddingValues),
        contentAlignment = Alignment.Center
    ) {
        TextBodyMedium(
            text = buttonText,
            color = textButton,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview
@Composable
fun PreviewLargeButtonTertiary() {
    ITTPizenTheme {
        Surface {
            LargeButtonTertiary(
                text = "See more",
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}
