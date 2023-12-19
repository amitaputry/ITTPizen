package com.ta.ittpizen.ui.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.component.text.TextBodySmall
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {},
    enable: Boolean = true,
    loading: Boolean = false
) {
    val buttonText = if (loading) "Loading..." else text
    val buttonEnabled = if (loading) false else enable
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(size = 100.dp))
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(vertical = 10.dp, horizontal = 28.dp)
            .clickable(buttonEnabled) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        TextBodySmall(
            text = buttonText,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Preview
@Composable
fun PreviewPrimaryButton() {
    ITTPizenTheme {
        Surface {
            PrimaryButton(
                text = "Let’s Go!"
            )
        }
    }
}
