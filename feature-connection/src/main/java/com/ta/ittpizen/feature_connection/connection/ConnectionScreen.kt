package com.ta.ittpizen.feature_connection.connection

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
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
import com.ta.ittpizen.domain.model.UserItem
import com.ta.ittpizen.domain.model.UserItemType
import com.ta.ittpizen.domain.utils.DataUserItem
import com.ta.ittpizen.ui.component.chip.SingleChipRow
import com.ta.ittpizen.ui.component.searchbar.DummySearchBar
import com.ta.ittpizen.ui.component.topappbar.BaseTopAppBar
import com.ta.ittpizen.ui.component.user.UserItem
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun ConnectionScreen(
    modifier: Modifier = Modifier,
    navigateToSearchConnectionScreen: () -> Unit = {},
    navigateToDetailUserScreen: (String) -> Unit = {},
) {

    val options = listOf("Students", "Alumni", "Lecturer", "Staff")
    var selectedOption by remember { mutableStateOf(options[0]) }
    val currentUserItemType by remember(key1 = selectedOption) {
        val userItemType = when (selectedOption) {
            options[0] -> UserItemType.Student
            options[1] -> UserItemType.Alumni
            options[2] -> UserItemType.Lecturer
            else -> UserItemType.Staff
        }
        mutableStateOf(userItemType)
    }

    val users = remember(key1 = currentUserItemType) {
        val users = mutableStateListOf<UserItem>()
        users.addAll(DataUserItem.generateUsersByType(currentUserItemType))
        users
    }

    val onOptionClick: (String) -> Unit = {
        selectedOption = it
    }

    val onButtonConnection: (UserItem) -> Unit = { user ->
        DataUserItem.connectToUser(user)
        users.clear()
        users.addAll(DataUserItem.generateUsersByType(currentUserItemType))
    }

    Scaffold(modifier = modifier) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            item {
                BaseTopAppBar(
                    title = "Connection"
                )
            }
            item {
                DummySearchBar(
                    placeholder = "Find your friends..",
                    onClick = navigateToSearchConnectionScreen,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            stickyHeader {
                SingleChipRow(
                    selectedOption = selectedOption,
                    options = options,
                    onSelected = onOptionClick,
                    chipContentPadding = PaddingValues(vertical = 8.dp, horizontal = 20.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
                    spaceBetweenItem = 10.dp
                )
            }
            items(items = users, key = { it.id }) {
                UserItem(
                    user = it,
                    onClick = { navigateToDetailUserScreen(it.id) },
                    onConnectClick = onButtonConnection,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
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
        