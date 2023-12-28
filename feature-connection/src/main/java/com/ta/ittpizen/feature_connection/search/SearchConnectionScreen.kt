package com.ta.ittpizen.feature_connection.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
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
import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.model.UserItem
import com.ta.ittpizen.ui.component.history.HistoryContent
import com.ta.ittpizen.ui.component.history.HistoryEmptyContent
import com.ta.ittpizen.ui.component.topappbar.DetailSearchAppBar
import com.ta.ittpizen.ui.component.user.UserItem
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun SearchConnectionScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
    navigateToDetailConnection: (String) -> Unit = {},
) {

    var query by remember { mutableStateOf("") }

    val histories = remember {
        mutableStateListOf("abdul hafiz ramadan", "amita", "afifa")
    }

    val onDeleteAllClick: () -> Unit = {
        histories.clear()
    }

    val onDeleteHistoryClick: (Int) -> Unit = {
        histories.remove(histories[it])
    }

    val dummyUsers = (1..20).map {
        val connected = it == 3
        UserItem(
            id = it.toString(),
            name = "Amita Putry Prasasti",
            type = "Student",
            connected = connected
        )
    }
    var users: Resource<List<UserItem>> by remember {
        mutableStateOf(Resource.Idle)
    }

    val onSearch: () -> Unit = {
        users = Resource.Success(data = dummyUsers)
    }

    Scaffold(
        topBar = {
            DetailSearchAppBar(
                query = query,
                onQueryChange = { query = it },
                placeholder = "Search...",
                onSearchClick = onSearch,
                onNavigationClick = navigateUp
            )
        },
        modifier = modifier
    ) { paddingValues ->
        AnimatedVisibility(
            visible = users is Resource.Idle,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            HistoryContent(
                histories = histories,
                modifier = Modifier.padding(paddingValues),
                onDeleteHistoryClick = onDeleteHistoryClick,
                onDeleteAllClick = onDeleteAllClick
            )
        }
        AnimatedVisibility(
            visible = users is Resource.Success && (users as Resource.Success<List<UserItem>>).data.isEmpty(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            HistoryEmptyContent(
                modifier = Modifier.padding(paddingValues)
            )
        }
        AnimatedVisibility(
            visible = users is Resource.Success,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            val data = (users as Resource.Success).data
            LazyColumn(
                contentPadding = PaddingValues(vertical = 20.dp),
                modifier = Modifier.padding(paddingValues),
            ) {
                items(items = data, key = { it.id }) { userItem ->
                    UserItem(
                        user = userItem,
                        onClick = { navigateToDetailConnection(it.id) },
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                    )
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewSearchConnectionScreen() {
    ITTPizenTheme {
        Surface {
            SearchConnectionScreen()
        }
    }
}
