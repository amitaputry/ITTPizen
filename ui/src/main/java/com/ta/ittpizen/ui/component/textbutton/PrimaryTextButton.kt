package com.ta.ittpizen.ui.component.textbutton

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.PrimaryRed

@Composable
fun PrimaryTextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {},
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight(700),
) {
    val buttonText = buildAnnotatedString {
        append(text)
    }
    ClickableText(
        text = buttonText,
        onClick = { onClick() },
        modifier = modifier,
        style = TextStyle(
            fontSize = fontSize,
            fontWeight = fontWeight,
            color = PrimaryRed,
            textAlign = TextAlign.Center,
            letterSpacing = 0.02.sp,
        )
    )
}

@Preview
@Composable
fun PreviewPrimaryTextButton() {
    ITTPizenTheme {
        Surface {
            PrimaryTextButton(
                text = "Skip",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
