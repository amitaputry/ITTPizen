package com.ta.ittpizen.ui.component.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun TextBodySmall(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    fontWeight: FontWeight = FontWeight(500),
    maxLines: Int = Int.MAX_VALUE,
    lineHeight: TextUnit = 16.sp
) {
    Text(
        text = text,
        fontSize = 12.sp,
        fontWeight = fontWeight,
        color = color,
        letterSpacing = 0.05.sp,
        lineHeight = lineHeight,
        overflow = TextOverflow.Ellipsis,
        maxLines = maxLines,
        modifier = modifier
    )
}

@Preview
@Composable
fun PreviewTextBodySmall() {
    ITTPizenTheme {
        Surface {
            TextBodySmall(
                text = "Selamat untuk Mahasiswa ITTP telah mendapat juara tingkat nasional, semangat terus!!",
                maxLines = 2,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
