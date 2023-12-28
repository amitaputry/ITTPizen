package com.ta.ittpizen.ui.component.topappbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.component.iconbutton.BaseIconButton
import com.ta.ittpizen.ui.component.searchbar.OutlinedSearchBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalMaterial3Api
@Composable
fun DetailSearchAppBar(
    modifier: Modifier = Modifier,
    query: String = "",
    onQueryChange: (String) -> Unit = {},
    placeholder: String = "",
    onSearchClick: () -> Unit = {},
    onNavigationClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .height(56.dp)
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 16.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BaseIconButton(
            icon = painterResource(id = R.drawable.ic_back),
            contentDescription = "Back",
            onClick = onNavigationClick
        )
        Spacer(modifier = Modifier.width(12.dp))
        OutlinedSearchBar(
            query = query,
            onQueryChange = onQueryChange,
            placeholder = placeholder,
            onSearchClick = onSearchClick,
            modifier = Modifier.weight(1f)
        )
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewDetailSearchAppBar() {
    ITTPizenTheme {
        Surface {
            DetailSearchAppBar(
                placeholder = "Search...",
                query = "fwefw"
            )
        }
    }
}
