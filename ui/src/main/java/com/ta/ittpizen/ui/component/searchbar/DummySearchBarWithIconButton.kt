package com.ta.ittpizen.ui.component.searchbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.component.iconbutton.FilledIconButton
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalMaterial3Api
@Composable
fun DummySearchBarWithIconButton(
    modifier: Modifier = Modifier,
    placeholder: String = "",
    onSearchBarClick: () -> Unit = {},
    icon: Painter = painterResource(id = R.drawable.ic_add_primary),
    contentDescription: String? = null,
    onButtonClick: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DummySearchBar(
            placeholder = placeholder,
            onClick = onSearchBarClick,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(10.dp))
        FilledIconButton(
            icon = icon,
            contentDescription = contentDescription,
            onClick = onButtonClick
        )
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewDummySearchBarWithIconButton() {
    ITTPizenTheme {
        Surface {
            DummySearchBarWithIconButton(
                placeholder = "Search",
                icon = painterResource(id = R.drawable.ic_add_primary),
                contentDescription = "Add message",
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            )
        }
    }
}
