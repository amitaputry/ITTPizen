package com.ta.ittpizen.ui.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.component.text.TextBodySmall
import com.ta.ittpizen.ui.theme.DisableColorGrey
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.PrimaryRed

@Composable
fun BaseChip(
    modifier: Modifier = Modifier,
    text: String,
    onSelected: (String) -> Unit = {},
    selected: Boolean = false
) {
    val borderColor = if (selected) PrimaryRed else DisableColorGrey
    val textColor = if (selected) Color.White else DisableColorGrey
    val backgroundColor = if (selected) PrimaryRed else Color.Transparent
    TextBodySmall(
        text = text,
        color = textColor,
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(size = 100.dp))
            .clickable { onSelected(text) }
            .background(backgroundColor)
            .padding(vertical = 8.dp, horizontal = 10.dp)
    )
}

@Preview
@Composable
fun PreviewBaseChip() {
    ITTPizenTheme {
        Surface {
            BaseChip(
                text = "All Post",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewBaseChipSelected() {
    ITTPizenTheme {
        Surface {
            BaseChip(
                text = "All Post",
                selected = true,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

