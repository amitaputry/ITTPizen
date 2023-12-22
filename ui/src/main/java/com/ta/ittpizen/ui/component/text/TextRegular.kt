package com.ta.ittpizen.ui.component.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun TextRegular(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        fontSize = 12.sp,
        lineHeight = 19.2.sp,
        letterSpacing = 0.04.sp,
        modifier = modifier,
        color = color,
        textAlign = textAlign
    )
}

@Preview
@Composable
fun PreviewTextRegular() {
    ITTPizenTheme {
        Surface {
            TextRegular(
                text = "(Optional)"
            )
        }
    }
}

