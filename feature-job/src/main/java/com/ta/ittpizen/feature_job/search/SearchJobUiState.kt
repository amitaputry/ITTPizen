package com.ta.ittpizen.feature_job.search

import androidx.paging.PagingData
import com.ta.ittpizen.domain.model.job.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SearchJobUiState(
    val query: String = "",
    val jobLoaded: Boolean = false,
    val jobs: Flow<PagingData<Job>> = emptyFlow()
)
