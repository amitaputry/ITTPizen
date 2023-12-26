package com.ta.ittpizen.ui.component.job

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.component.iconbutton.SmallIconButton
import com.ta.ittpizen.ui.component.text.TextBodySmall
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.SecondDarkGrey

@Composable
fun JobItemFooter(
    modifier: Modifier = Modifier,
    date: String,
    saved: Boolean = false,
    onSaveClick: () -> Unit = {}
) {
    val iconButton = if (saved) R.drawable.ic_saved else R.drawable.ic_save
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextBodySmall(text = date, color = SecondDarkGrey)
        SmallIconButton(
            icon = painterResource(id = iconButton),
            contentDescription = "Save job",
            onClick = onSaveClick,
            modifier = Modifier.offset(x = 8.dp)
        )
    }
}

@Preview
@Composable
fun PreviewJobItemFooter() {
    ITTPizenTheme {
        Surface {
            JobItemFooter(
                date = "12 days ago",
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}

