package com.ta.ittpizen.ui.component.job

import androidx.compose.foundation.background
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
import com.ta.ittpizen.ui.theme.ColorText
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun JobCharacteristicItem(
    modifier: Modifier = Modifier,
    text: String
) {
    TextBodySmall(
        text = text,
        color = ColorText,
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .background(color = Color(0xFFF0F0F0))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}

@Preview
@Composable
fun PreviewJobCharacteristicItem() {
    ITTPizenTheme {
        Surface {
            JobCharacteristicItem(
                text = "Onsite",
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}
