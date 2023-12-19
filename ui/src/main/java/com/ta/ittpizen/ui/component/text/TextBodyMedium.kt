package com.ta.ittpizen.ui.component.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun TextBodyMedium(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = Int.MAX_VALUE,
) {
    Text(
        text = text,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        color = color,
        letterSpacing = 0.04.sp,
        textAlign = textAlign,
        maxLines = maxLines,
        modifier = modifier
    )
}

@Preview
@Composable
fun PreviewTextBodyMedium() {
    ITTPizenTheme {
        Surface {
            TextBodyMedium(
                text = "Enter your username",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
