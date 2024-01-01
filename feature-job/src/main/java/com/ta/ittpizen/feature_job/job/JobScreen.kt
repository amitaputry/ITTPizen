package com.ta.ittpizen.feature_job.job

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.domain.model.JobItem
import com.ta.ittpizen.domain.utils.DataJobItem
import com.ta.ittpizen.ui.component.chip.SingleChipRow
import com.ta.ittpizen.ui.component.job.JobItem
import com.ta.ittpizen.ui.component.searchbar.DummySearchBarWithIconButton
import com.ta.ittpizen.ui.component.topappbar.BaseTopAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalLayoutApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun JobScreen(
    modifier: Modifier = Modifier,
    navigateToAddJobScreen: () -> Unit = {},
    navigateToDetailJobScreen: (String) -> Unit = {},
    navigateToSearchJobScreen: () -> Unit = {}
) {

    val options = listOf("For you", "Remote", "Onsite", "Full time", "Part Time", "Internship", "Volunteer")
    var selectedOption by remember { mutableStateOf(options[0]) }

    val jobItems = remember(key1 = selectedOption) {
        val users = mutableStateListOf<JobItem>()
        val jobs = when (selectedOption) {
            options[0] -> DataJobItem.getAllJobsItem()
            else -> DataJobItem.getJobsItemByCharacteristic(selectedOption)
        }
        users.addAll(jobs)
        users
    }

    val onOptionClick: (String) -> Unit = {
        selectedOption = it
    }

    val onButtonSaveClick: (JobItem) -> Unit = { jobItem ->
        val updatedJob = DataJobItem.savedOrUnsavedJob(jobItem)!!
        jobItems.replaceAll {
            if (it.id == updatedJob.id) updatedJob else it
        }
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
            items(items = jobItems, key = { it.id }) {
                JobItem(
                    jobItem = it,
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
        