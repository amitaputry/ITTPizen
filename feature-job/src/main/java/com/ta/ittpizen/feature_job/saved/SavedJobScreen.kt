package com.ta.ittpizen.feature_job.saved

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ta.ittpizen.domain.model.JobItem
import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.utils.DataJobItem
import com.ta.ittpizen.feature_job.component.EmptyJobContent
import com.ta.ittpizen.ui.component.topappbar.DetailTopAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalFoundationApi
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Composable
fun SavedJobScreen(
    modifier: Modifier = Modifier,
    userId: String = "",
    navigateUp: () -> Unit = {},
    navigateToDetailJobScreen: (String) -> Unit = {},
) {

    var jobs: Resource<List<JobItem>> by remember {
        mutableStateOf(Resource.Success(DataJobItem.getSavedJobs()))
    }

    val unSaveJobItem: (JobItem) -> Unit = {
        val job = DataJobItem.savedOrUnsavedJob(it)
        if (job != null && jobs is Resource.Success<List<JobItem>>) {
            val mutableJob = (jobs as Resource.Success<List<JobItem>>).data.toMutableList()
            mutableJob.remove(it)
            jobs = Resource.Success(mutableJob)
        }
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
        AnimatedVisibility(jobs is Resource.Success && (jobs as Resource.Success<List<JobItem>>).data.isEmpty()) {
            EmptyJobContent(
                modifier = Modifier.padding(paddingValues),
                title = "No Saved Job :("
            )
        }
        AnimatedVisibility (jobs is Resource.Success && (jobs as Resource.Success<List<JobItem>>).data.isNotEmpty()) {
            val data = (jobs as Resource.Success<List<JobItem>>).data
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(items = data, key = { it.id }) { job ->
//                    JobItem(
//                        jobItem = job,
//                        onClick = { navigateToDetailJobScreen(it.id) },
//                        onSaveClick = unSaveJobItem,
//                        modifier = Modifier
//                            .padding(horizontal = 20.dp, vertical = 10.dp)
//                            .animateItemPlacement()
//                    )
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
