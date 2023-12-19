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
import com.ta.ittpizen.ui.theme.SecondDarkGrey

@Composable
fun TextBodyLarge(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        fontSize = 16.sp,
        color = color,
        textAlign = textAlign,
        letterSpacing = 0.04.sp,
        modifier = modifier
    )
}

@Preview
@Composable
fun PreviewTextBodyLarge() {
    ITTPizenTheme {
        Surface {
            TextBodyLarge(
                text = "Let’s connect, sharing information and job with students, alumni, lecturer and other civitas at ITTPizen now.",
                textAlign = TextAlign.Center,
                color = SecondDarkGrey
            )
        }
    }
}
