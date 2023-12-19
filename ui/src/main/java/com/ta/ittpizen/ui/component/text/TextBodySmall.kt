package com.ta.ittpizen.ui.component.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun TextBodySmall(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
) {
    Text(
        text = text,
        fontSize = 12.sp,
        fontWeight = FontWeight(500),
        color = color,
        letterSpacing = 0.05.sp,
        modifier = modifier
    )
}

@Preview
@Composable
fun PreviewTextBodySmall() {
    ITTPizenTheme {
        Surface {
            TextBodySmall(
                text = "Let’s Go!",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
