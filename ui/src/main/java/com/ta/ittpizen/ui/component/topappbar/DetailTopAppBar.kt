package com.ta.ittpizen.ui.component.topappbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.component.text.TextTitleLarge
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalMaterial3Api
@Composable
fun DetailTopAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    onNavigationClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            TextTitleLarge(
                text = title,
                color = Color(0xFF333538),
                modifier = Modifier.offset(y = (-1).dp)
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back"
                )
            }
        },
        modifier = modifier
    )
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewDetailTopAppBar() {
    ITTPizenTheme {
        Surface {
            DetailTopAppBar(
                title = "Register"
            )
        }
    }
}
