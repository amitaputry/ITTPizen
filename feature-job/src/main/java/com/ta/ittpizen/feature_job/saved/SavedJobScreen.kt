package com.ta.ittpizen.feature_job.saved

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.ta.ittpizen.domain.model.job.Job
import com.ta.ittpizen.domain.model.preference.UserPreference
import com.ta.ittpizen.ui.component.content.EmptyContent
import com.ta.ittpizen.ui.component.job.JobItem
import com.ta.ittpizen.ui.component.topappbar.DetailTopAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.PrimaryRed
import org.koin.androidx.compose.koinViewModel

@ExperimentalFoundationApi
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Composable
fun SavedJobScreen(
    modifier: Modifier = Modifier,
    viewModel: SavedJobViewModel = koinViewModel(),
    userId: String = "",
    navigateUp: () -> Unit = {},
    navigateToDetailJobScreen: (String) -> Unit = {},
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val userPreference by viewModel.userPreference.collectAsStateWithLifecycle(initialValue = UserPreference())
    val jobs = uiState.jobs.collectAsLazyPagingItems()

    val unSaveJobItem: (Job) -> Unit = {
        viewModel.unSaveJob(
            token = userPreference.accessToken,
            jobId = it.id
        )
        viewModel.getSavedJob(userPreference.accessToken)
    }

    LaunchedEffect(key1 = userPreference) {
        if (userPreference.accessToken.isEmpty()) return@LaunchedEffect
        viewModel.getSavedJob(userPreference.accessToken)
    }

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Saved",
                onNavigationClick = navigateUp
            )
        },
        modifier = modifier
    ) { paddingValues ->
        AnimatedVisibility(
            visible = jobs.loadState.refresh is LoadState.Loading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = PrimaryRed)
            }
        }
        AnimatedVisibility(
            visible = jobs.loadState.refresh is LoadState.NotLoading && jobs.itemCount == 0,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            EmptyContent(
                modifier = Modifier.padding(paddingValues),
                title = "No Saved Job :("
            )
        }
        AnimatedVisibility (
            visible = jobs.loadState.refresh is LoadState.NotLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(count = jobs.itemCount) {
                    val job = jobs[it]
                    if (job != null) {
                        JobItem(
                            jobItem = job,
                            onClick = { navigateToDetailJobScreen(it.id) },
                            onSaveClick = unSaveJobItem,
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

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@ExperimentalLayoutApi
@Preview
@Composable
fun PreviewSavedJobScreen() {
    ITTPizenTheme {
        Surface {
            SavedJobScreen()
        }
    }
}
