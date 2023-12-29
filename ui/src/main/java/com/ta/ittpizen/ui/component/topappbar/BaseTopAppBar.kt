package com.ta.ittpizen.ui.component.topappbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.component.text.TextTitleLarge
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalMaterial3Api
@Composable
fun BaseTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ) {
        TextTitleLarge(
            text = title,
            color = Color(0xFF333538),
            modifier = Modifier.offset(y = (-1).dp)
        )
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewBaseTopAppBar() {
    ITTPizenTheme {
        Surface {
            BaseTopAppBar(
                title = "Register"
            )
        }
    }
}
