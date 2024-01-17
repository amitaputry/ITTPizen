package com.ta.ittpizen.feature_job.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import com.ta.ittpizen.domain.model.JobItem
import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.model.UserItem
import com.ta.ittpizen.domain.utils.DataJobItem
import com.ta.ittpizen.ui.component.history.HistoryContent
import com.ta.ittpizen.ui.component.history.HistoryEmptyContent
import com.ta.ittpizen.ui.component.topappbar.DetailSearchAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalLayoutApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun SearchJobScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
    navigateToDetailJob: (String) -> Unit = {},
) {

    var query by remember { mutableStateOf("") }

    val histories = remember {
        mutableStateListOf("management", "web", "android developer")
    }

    val onDeleteAllClick: () -> Unit = {
        histories.clear()
    }

    val onDeleteHistoryClick: (Int) -> Unit = {
        histories.remove(histories[it])
    }

    var jobs: Resource<List<JobItem>> by remember {
        mutableStateOf(Resource.Idle)
    }

    val onSearch: () -> Unit = {
        jobs = Resource.Success(data = DataJobItem.searchJob(query))
    }

    val onButtonSaveClick: (JobItem) -> Unit = { jobItem ->
        val updatedJob = DataJobItem.savedOrUnsavedJob(jobItem)
        if (updatedJob != null && jobs is Resource.Success<List<JobItem>>) {
            val mutableJob = (jobs as Resource.Success<List<JobItem>>).data.toMutableList()
            mutableJob.replaceAll {
                if (it.id == jobItem.id) updatedJob else it
            }
            jobs = Resource.Success(mutableJob)
        }
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
            visible = jobs is Resource.Idle,
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
            visible = jobs is Resource.Success && (jobs as Resource.Success<List<UserItem>>).data.isEmpty(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            HistoryEmptyContent(
                modifier = Modifier.padding(paddingValues),
                description = "No Job Vacancy Found :("
            )
        }
        AnimatedVisibility(
            visible = jobs is Resource.Success,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            val data = (jobs as Resource.Success).data
            LazyColumn(
                contentPadding = PaddingValues(vertical = 20.dp),
                modifier = Modifier.padding(paddingValues),
            ) {
                items(items = data, key = { it.id }) { jobItem ->
//                    JobItem(
//                        jobItem = jobItem,
//                        onClick = { navigateToDetailJob(it.id) },
//                        onSaveClick = onButtonSaveClick,
//                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
//                    )
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
