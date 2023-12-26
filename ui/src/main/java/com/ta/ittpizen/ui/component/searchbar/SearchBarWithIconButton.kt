package com.ta.ittpizen.ui.component.searchbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun SearchBarWithIconButton(
    modifier: Modifier = Modifier,
    query: String = "",
    onQueryChange: (String) -> Unit = {},
    placeholder: String = "",
    enabled: Boolean = true,
    isError: Boolean = false,
    icon: Painter = painterResource(id = R.drawable.ic_add_primary),
    contentDescription: String? = null,
    onButtonClick: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BaseSearchBar(
            query = query,
            onQueryChange = onQueryChange,
            placeholder = placeholder,
            enabled = enabled,
            isError = isError,
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
fun PreviewSearchBarWithIconButton() {
    ITTPizenTheme {
        Surface {
            var query by remember { mutableStateOf("") }
            SearchBarWithIconButton(
                query = query,
                onQueryChange = { query = it },
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
