package com.ta.ittpizen.ui.component.topappbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.component.iconbutton.BaseIconButton
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun DetailTransparentTopAppBar(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .height(56.dp)
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BaseIconButton(
            icon = painterResource(id = R.drawable.ic_back_white),
            contentDescription = "Back",
            onClick = navigateUp,
            modifier = Modifier.offset(x = (-4).dp)
        )
    }
}

@Preview
@Composable
fun PreviewDetailTransparentTopAppBar() {
    ITTPizenTheme {
        Surface(color = Color.Black) {
            DetailTransparentTopAppBar(

            )
        }
    }
}
