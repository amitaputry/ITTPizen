package com.ta.ittpizen.feature_connection.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.ta.ittpizen.domain.model.connection.Connection
import com.ta.ittpizen.domain.model.preference.UserPreference
import com.ta.ittpizen.ui.component.history.HistoryContent
import com.ta.ittpizen.ui.component.history.HistoryEmptyContent
import com.ta.ittpizen.ui.component.topappbar.DetailSearchAppBar
import com.ta.ittpizen.ui.component.user.UserItem
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.PrimaryRed
import org.koin.androidx.compose.koinViewModel

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun SearchConnectionScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchConnectionViewModel = koinViewModel(),
    navigateUp: () -> Unit = {},
    navigateToDetailConnection: (String) -> Unit = {},
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val connectionLoaded = uiState.connectionLoaded
    val query = uiState.query
    val connections = uiState.connections.collectAsLazyPagingItems()

    val userPreference by viewModel.userPreference.collectAsStateWithLifecycle(initialValue = UserPreference())

    val histories = remember {
        mutableStateListOf("abdul hafiz ramadan", "amita", "afifa")
    }

    val onDeleteAllClick: () -> Unit = {
        histories.clear()
    }

    val onDeleteHistoryClick: (Int) -> Unit = { histories.remove(histories[it]) }

    val onSearch: () -> Unit = {
        val token = userPreference.accessToken
        if (token.isNotEmpty()) {
            viewModel.searchConnection(token, query)
        }
    }

    val onButtonConnection: (Connection) -> Unit = { connection ->
        val token = userPreference.accessToken
        val userId = connection.id
        if (connection.connected) {
            viewModel.deleteConnection(token, userId)
        } else {
            viewModel.createConnection(token, userId)
        }
    }

    LaunchedEffect(key1 = Unit) {
        val token = userPreference.accessToken
        if (token.isNotEmpty() && query.isNotEmpty()) {
            connections.refresh()
        }
    }

    Scaffold(
        topBar = {
            DetailSearchAppBar(
                query = query,
                onQueryChange = viewModel::updateQuery,
                placeholder = "Search...",
                onSearchClick = onSearch,
                onNavigationClick = navigateUp
            )
        },
        modifier = modifier
    ) { paddingValues ->
        AnimatedVisibility(
            visible = connectionLoaded.not(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            HistoryContent(
                histories = histories,
                modifier = Modifier.padding(paddingValues),
                onHistoryClick = {
                    viewModel.updateQuery(query)
                    onSearch()
                },
                onDeleteHistoryClick = onDeleteHistoryClick,
                onDeleteAllClick = onDeleteAllClick
            )
        }
        AnimatedVisibility(
            visible = connections.loadState.refresh is LoadState.Loading && connectionLoaded,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(color = PrimaryRed)
            }
        }
        AnimatedVisibility(
            visible = connections.loadState.refresh is LoadState.NotLoading && connections.itemCount == 0,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            HistoryEmptyContent(
                modifier = Modifier.padding(paddingValues),
                description = "No Friends found :("
            )
        }
        AnimatedVisibility(
            visible = connections.loadState.refresh is LoadState.NotLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 20.dp),
                modifier = Modifier.padding(paddingValues),
            ) {
                items(count = connections.itemCount, key = { connections[it]?.id ?: it }) {
                    val user = connections[it]
                    if (user != null) {
                        UserItem(
                            user = user,
                            onClick = { navigateToDetailConnection(user.id) },
                            onConnectClick = onButtonConnection,
                            modifier = Modifier
                                .padding(horizontal = 20.dp, vertical = 10.dp)
                                .animateItemPlacement()
                        )
                    }
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
