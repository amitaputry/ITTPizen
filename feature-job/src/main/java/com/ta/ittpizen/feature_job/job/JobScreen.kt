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
import com.ta.ittpizen.domain.entity.JobItem
import com.ta.ittpizen.ui.component.chip.SingleChipRow
import com.ta.ittpizen.ui.component.job.JobItem
import com.ta.ittpizen.ui.component.searchbar.SearchBarWithIconButton
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

    var query by remember { mutableStateOf("") }

    val options = listOf("For you", "Remote", "Onsite", "Full time", "Part Time")
    var selectedOption by remember { mutableStateOf(options[0]) }

    val jobItems = remember(key1 = selectedOption) {
        val users = mutableStateListOf<JobItem>()
        users.addAll(
            (1..20).map {
                JobItem(
                    id = it.toString(),
                    name = "Asisten Praktikum Basis Data",
                    characteristics = listOf("Onsite", "Part time", "0 - 1 year", "Min D3"),
                    company = "Fakultas Informatika",
                    date = "12 days ago",
                    location = "Jl. DI Panjaitan No.128, Banyumas, Jawa Tengah",
                    saved = it == 3
                )
            }
        )
        users
    }

    val onOptionClick: (String) -> Unit = {
        selectedOption = it
    }

    val onButtonSaveClick: (JobItem) -> Unit = { jobItem ->
        val updatedUser = jobItems.map {
            if (jobItem.id == it.id) jobItem.copy(saved = jobItem.saved.not()) else it
        }
        jobItems.clear()
        jobItems.addAll(updatedUser)
    }

    Scaffold(modifier = modifier) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            item {
                BaseTopAppBar(
                    title = "Job Vacancy"
                )
            }
            item {
                SearchBarWithIconButton(
                    query = query,
                    onQueryChange = { query = it },
                    placeholder = "Search...",
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
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
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
        