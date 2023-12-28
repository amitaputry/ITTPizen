package com.ta.ittpizen.ui.component.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ta.ittpizen.ui.component.text.TextBodyMedium
import com.ta.ittpizen.ui.component.textbutton.TextButton
import com.ta.ittpizen.ui.theme.ColorDanger
import com.ta.ittpizen.ui.theme.ColorDark
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun HistoryHeader(
    modifier: Modifier = Modifier,
    onDeleteAllClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextBodyMedium(
            text = "Recent searches",
            color = ColorDark,
            fontWeight = FontWeight.Medium
        )
        TextButton(
            text = "Delete all",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = ColorDanger,
            onClick = onDeleteAllClick
        )
    }
}

@Preview
@Composable
fun PreviewHistoryHeader() {
    ITTPizenTheme {
        Surface {
            HistoryHeader(modifier = Modifier.padding(20.dp))
        }
    }
}
