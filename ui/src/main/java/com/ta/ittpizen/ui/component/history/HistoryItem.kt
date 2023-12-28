package com.ta.ittpizen.ui.component.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.component.iconbutton.SmallIconButton
import com.ta.ittpizen.ui.component.text.TextBodyMedium
import com.ta.ittpizen.ui.theme.ColorDark
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun HistoryItem(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (String) -> Unit = {},
    onDelete: (String) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(text) }
            .padding(vertical = 10.dp)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_history),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(12.dp))
        TextBodyMedium(text = text, color = ColorDark)
        Spacer(modifier = Modifier.weight(1f))
        SmallIconButton(
            icon = painterResource(id = R.drawable.ic_delete),
            contentDescription = "Delete",
            onClick = { onDelete(text) },
            modifier = Modifier.offset(x = 4.dp)
        )
    }
}

@Preview
@Composable
fun PreviewHistoryItem() {
    ITTPizenTheme {
        Surface {
            HistoryItem(
                text = "abdul hafiz ramadan",
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}
