package com.ta.ittpizen.ui.component.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.component.text.TextBodyMedium
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun HistoryEmptyContent(
    modifier: Modifier = Modifier,
    description: String = ""
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_search_not_found),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextBodyMedium(text = description)
    }
}

@Preview
@Composable
fun PreviewHistoryEmptyContent() {
    ITTPizenTheme {
        Surface {
            HistoryEmptyContent()
        }
    }
}
