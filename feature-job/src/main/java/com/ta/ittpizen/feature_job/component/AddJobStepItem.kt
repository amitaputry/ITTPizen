package com.ta.ittpizen.feature_job.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.component.text.TextBodyMedium
import com.ta.ittpizen.ui.theme.DisableColorGrey
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.PrimaryRed

@Composable
fun AddJobStepItem(
    modifier: Modifier = Modifier,
    step: String = "",
    active: Boolean = false
) {
    val background = if (active) Color(0xFFFFD8D8) else Color.Transparent
    val borderColor = if (active) Color(0xFFFFD8D8) else DisableColorGrey
    TextBodyMedium(
        text = step,
        color = PrimaryRed,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center,
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(size = 100.dp))
            .background(background)
            .padding(horizontal = 20.dp, vertical = 8.dp)
    )
}

@Preview
@Composable
fun PreviewAddJobStepItem() {
    ITTPizenTheme {
        Surface {
            AddJobStepItem(
                step = "1. General",
                active = false
            )
        }
    }
}

