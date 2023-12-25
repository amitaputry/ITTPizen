package com.ta.ittpizen.feature_connection

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.domain.entity.UserItem
import com.ta.ittpizen.ui.component.chip.SingleChipRow
import com.ta.ittpizen.ui.component.searchbar.BaseSearchBar
import com.ta.ittpizen.ui.component.topappbar.BaseTopAppBar
import com.ta.ittpizen.ui.component.user.UserItem
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun ConnectionScreen(
    modifier: Modifier = Modifier
) {

    var query by remember { mutableStateOf("") }

    val options = listOf("Students", "Alumni", "Lecturer", "Staff", "Academic", "Organization")
    var selectedOption by remember { mutableStateOf(options[0]) }

    val users = remember(key1 = selectedOption) {
        val users = mutableStateListOf<UserItem>()
        users.addAll(
            (1..20).map {
                val connected = it == 3
                UserItem(
                    id = it.toString(),
                    name = "Amita Putry Prasasti",
                    type = selectedOption,
                    connected = connected
                )
            }
        )
        users
    }

    val onOptionClick: (String) -> Unit = {
        selectedOption = it
    }

    val onButtonConnection: (UserItem) -> Unit = { user ->
        val updatedUser = users.map {
            if (user.id == it.id) user.copy(connected = user.connected.not()) else it
        }
        users.clear()
        users.addAll(updatedUser)
    }

    Scaffold(modifier = modifier) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            item {
                BaseTopAppBar(
                    title = "Connection"
                )
            }
            item {
                BaseSearchBar(
                    value = query,
                    onValueChange = { query = it },
                    placeholder = "Find your friends..",
                    singleLine = true,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                )
            }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            stickyHeader {
                SingleChipRow(
                    selectedOption = selectedOption,
                    options = options,
                    onSelected = onOptionClick,
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
                )
            }
            items(items = users, key = { it.id }) {
                UserItem(
                    user = it,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                    onConnectClick = onButtonConnection
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewConnectionScreen() {
    ITTPizenTheme {
        Surface {
            ConnectionScreen()
        }
    }
}
        