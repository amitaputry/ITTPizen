package com.ta.ittpizen.feature_job.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import com.ta.ittpizen.domain.model.job.Job
import com.ta.ittpizen.domain.model.preference.UserPreference
import com.ta.ittpizen.ui.component.history.HistoryContent
import com.ta.ittpizen.ui.component.history.HistoryEmptyContent
import com.ta.ittpizen.ui.component.job.JobItem
import com.ta.ittpizen.ui.component.topappbar.DetailSearchAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.PrimaryRed
import org.koin.androidx.compose.koinViewModel

@ExperimentalLayoutApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun SearchJobScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchJobViewModel = koinViewModel(),
    navigateUp: () -> Unit = {},
    navigateToDetailJob: (String) -> Unit = {},
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val jobLoaded = uiState.jobLoaded
    val query = uiState.query
    val jobs = uiState.jobs.collectAsLazyPagingItems()

    val userPreference by viewModel.userPreference.collectAsStateWithLifecycle(initialValue = UserPreference())


    val histories = remember {
        mutableStateListOf("management", "web", "android developer", "project management", "fullstack")
    }

    var showSeeMoreButton by remember { mutableStateOf(histories.isNotEmpty()) }

    var historiesToShow by remember {
        val history = if (histories.size > 3) histories.take(3) else histories
        mutableStateOf(history)
    }

    val onDeleteAllClick: () -> Unit = {
        histories.clear()
    }

    val onDeleteHistoryClick: (Int) -> Unit = {
        histories.remove(histories[it])
    }

    val onHistoryClick: (String) -> Unit = { history ->
        viewModel.updateQuery(history)
        val token = userPreference.accessToken
        if (token.isNotEmpty()) {
            viewModel.searchJob(token, history)
        }
    }

    val onSeeMoreClick: () -> Unit = {
        historiesToShow = histories
        showSeeMoreButton = false
    }

    val onSearch: () -> Unit = {
        val token = userPreference.accessToken
        if (token.isNotEmpty()) {
            viewModel.searchJob(token, query)
        }
    }

    val onButtonSaveClick: (Job) -> Unit = { jobItem ->
        val token = userPreference.accessToken
        val jobId = jobItem.id
        if (jobItem.saved) {
            viewModel.unSaveJob(token, jobId)
        } else {
            viewModel.saveJob(token, jobId)
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
            visible = jobLoaded.not(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            HistoryContent(
                modifier = Modifier.padding(paddingValues),
                showSeeMoreButton = showSeeMoreButton,
                histories = historiesToShow,
                onDeleteHistoryClick = onDeleteHistoryClick,
                onDeleteAllClick = onDeleteAllClick,
                onSeeMoreClick = onSeeMoreClick,
                onHistoryClick = onHistoryClick
            )
        }
        AnimatedVisibility(
            visible = jobs.loadState.refresh is LoadState.Loading && jobLoaded,
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
            visible = jobs.loadState.refresh is LoadState.NotLoading && jobs.itemCount == 0,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            HistoryEmptyContent(
                modifier = Modifier.padding(paddingValues),
                description = "No Job Vacancy Found :("
            )
        }
        AnimatedVisibility(
            visible = jobs.loadState.refresh is LoadState.NotLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 20.dp),
                modifier = Modifier.padding(paddingValues),
            ) {
                items(count = jobs.itemCount, key = { it }) {
                    val jobItem = jobs[it]
                    if (jobItem != null) {
                        JobItem(
                            jobItem = jobItem,
                            onClick = { navigateToDetailJob(jobItem.id) },
                            onSaveClick = onButtonSaveClick,
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewSearchJobScreen() {
    ITTPizenTheme {
        Surface {
            SearchJobScreen()
        }
    }
}
