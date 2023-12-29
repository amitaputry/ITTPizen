package com.ta.ittpizen.feature_job.saved

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.domain.model.JobItem
import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.feature_job.component.EmptySavedJobContent
import com.ta.ittpizen.ui.component.job.JobItem
import com.ta.ittpizen.ui.component.topappbar.DetailTopAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Composable
fun SavedJobScreen(
    modifier: Modifier = Modifier,
    userId: String = "",
    navigateUp: () -> Unit = {},
    navigateToDetailJobScreen: (String) -> Unit = {},
) {

    val dummyJobs = (1..10).map {
        JobItem(
            id = it.toString(),
            name = "Asisten Praktikum Basis Data",
            characteristics = listOf("Onsite", "Part time", "0 - 1 year", "Min D3"),
            company = "Fakultas Informatika",
            date = "12 days ago",
            location = "Jl. DI Panjaitan No.128, Banyumas, Jawa Tengah",
            saved = true
        )
    }

    val jobs: Resource<List<JobItem>> = Resource.Success(dummyJobs)

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Saved",
                onNavigationClick = navigateUp
            )
        },
        modifier = modifier
    ) { paddingValues ->
        if (jobs is Resource.Success && jobs.data.isEmpty()) {
            EmptySavedJobContent(
                modifier = Modifier.padding(paddingValues)
            )
        }
        if (jobs is Resource.Success && jobs.data.isNotEmpty()) {
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(items = jobs.data, key = { it.id }) { job ->
                    JobItem(
                        jobItem = job,
                        onClick = { navigateToDetailJobScreen(it.id) },
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                    )
                }
            }
        }
    }
}

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
