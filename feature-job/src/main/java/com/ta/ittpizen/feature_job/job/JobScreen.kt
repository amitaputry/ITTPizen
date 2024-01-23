package com.ta.ittpizen.feature_job.job

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.ta.ittpizen.domain.model.job.Job
import com.ta.ittpizen.domain.model.preference.UserPreference
import com.ta.ittpizen.ui.component.chip.SingleChipRow
import com.ta.ittpizen.ui.component.content.EmptyContent
import com.ta.ittpizen.ui.component.job.JobItem
import com.ta.ittpizen.ui.component.searchbar.DummySearchBarWithIconButton
import com.ta.ittpizen.ui.component.topappbar.BaseTopAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.PrimaryRed
import org.koin.androidx.compose.koinViewModel

@ExperimentalLayoutApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun JobScreen(
    modifier: Modifier = Modifier,
    viewModel: JobViewModel = koinViewModel(),
    navigateToAddJobScreen: () -> Unit = {},
    navigateToDetailJobScreen: (String) -> Unit = {},
    navigateToSearchJobScreen: () -> Unit = {}
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val userPreference by viewModel.userPreference.collectAsStateWithLifecycle(initialValue = UserPreference())

    val jobLoaded = uiState.jobLoaded
    val jobs = uiState.jobs.collectAsLazyPagingItems()

    val workplaceTypes = listOf("Remote", "Onsite")
    val jobTypes = listOf("FullTime", "PartTime", "Internship", "Volunteer")
    val options = listOf("For you") + workplaceTypes + jobTypes

    var selectedOption by remember { mutableStateOf(options[0]) }

    val workplaceType by remember(key1 = selectedOption) {
        val value = if (workplaceTypes.contains(selectedOption)) selectedOption else ""
        mutableStateOf(value)
    }

    val jobType by remember(key1 = selectedOption) {
        val value = if (jobTypes.contains(selectedOption)) selectedOption else ""
        mutableStateOf(value)
    }

    val onOptionClick: (String) -> Unit = {
        selectedOption = it

    }

    val onButtonSaveClick: (Job) -> Unit = { job ->
        val token = userPreference.accessToken
        val jobId = job.id
        if (job.saved) {
            viewModel.unSaveJob(token, jobId)
        } else {
            viewModel.saveJob(token, jobId)
        }
    }

    LaunchedEffect(key1 = userPreference) {
        if (userPreference.accessToken.isEmpty()) return@LaunchedEffect
        if (jobLoaded) {
            jobs.refresh()
            return@LaunchedEffect
        }
        viewModel.getAllJob(userPreference.accessToken, workplaceType = "", jobType = "")
    }

    LaunchedEffect(key1 = selectedOption) {
        if (userPreference.accessToken.isEmpty()) return@LaunchedEffect
        viewModel.getAllJob(userPreference.accessToken, workplaceType = workplaceType, jobType = jobType)
    }

    Scaffold(modifier = modifier) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            item {
                BaseTopAppBar(
                    title = "Job Vacancy"
                )
            }
            item {
                DummySearchBarWithIconButton(
                    placeholder = "Search...",
                    onSearchBarClick = navigateToSearchJobScreen,
                    icon = painterResource(id = com.ta.ittpizen.ui.R.drawable.ic_add_job),
                    contentDescription = "Add Job",
                    onButtonClick = navigateToAddJobScreen,
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
            if (jobs.loadState.refresh is LoadState.Loading) {
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
            if (jobs.itemCount == 0 && jobs.loadState.refresh is LoadState.NotLoading) {
                item {
                    EmptyContent(
                        title = "There is no job :(",
                        modifier = Modifier.padding(top = 130.dp)
                    )
                }
            }
            items(count = jobs.itemCount) {
                val job = jobs[it]
                if (job != null) {
                    JobItem(
                        jobItem = job,
                        onClick = { navigateToDetailJobScreen(it.id) },
                        onSaveClick = onButtonSaveClick,
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                            .animateItemPlacement()
                    )
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
fun PreviewJobScreen() {
    ITTPizenTheme {
        Surface {
            JobScreen()
        }
    }
}
        