package com.ta.ittpizen.feature_connection.connection

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.ta.ittpizen.domain.model.connection.Connection
import com.ta.ittpizen.domain.model.preference.UserPreference
import com.ta.ittpizen.ui.component.chip.SingleChipRow
import com.ta.ittpizen.ui.component.content.EmptyContent
import com.ta.ittpizen.ui.component.searchbar.DummySearchBar
import com.ta.ittpizen.ui.component.topappbar.BaseTopAppBar
import com.ta.ittpizen.ui.component.user.UserItem
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.PrimaryRed
import org.koin.androidx.compose.koinViewModel

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun ConnectionScreen(
    modifier: Modifier = Modifier,
    viewModel: ConnectionViewModel = koinViewModel(),
    navigateToSearchConnectionScreen: () -> Unit = {},
    navigateToDetailUserScreen: (String) -> Unit = {},
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val userPreference by viewModel.userPreference.collectAsStateWithLifecycle(initialValue = UserPreference())

    val connectionLoaded = uiState.connectionLoaded
    val connections = uiState.connections.collectAsLazyPagingItems()

    val options = listOf("Student", "Alumni", "Lecturer", "Staff")
    var selectedOption by remember { mutableStateOf(options[0]) }

    val onOptionClick: (String) -> Unit = {
        selectedOption = it
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

    LaunchedEffect(key1 = userPreference) {
        if (userPreference.accessToken.isEmpty()) return@LaunchedEffect
        viewModel.getAllConnection(userPreference.accessToken, type = selectedOption)
    }

    LaunchedEffect(key1 = selectedOption) {
        if (userPreference.accessToken.isEmpty()) return@LaunchedEffect
        viewModel.getAllConnection(userPreference.accessToken, type = selectedOption)
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
            if (connections.loadState.refresh is LoadState.Loading) {
                item {
                    Box(
                        modifier = Modifier
                            .height(250.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        CircularProgressIndicator(color = PrimaryRed)
                    }
                }
            }
            if (connections.itemCount == 0 && connections.loadState.refresh is LoadState.NotLoading) {
                item {
                    EmptyContent(
                        title = "There is connection :(",
                        modifier = Modifier.padding(top = 130.dp)
                    )
                }
            }
            items(count = connections.itemCount, key = { connections[it]?.id ?: it }) {
                val connection = connections[it]
                if (connection != null) {
                    UserItem(
                        user = connection,
                        onClick = { navigateToDetailUserScreen(it.id) },
                        onConnectClick = onButtonConnection,
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
fun PreviewConnectionScreen() {
    ITTPizenTheme {
        Surface {
            ConnectionScreen()
        }
    }
}
        