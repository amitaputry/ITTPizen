package com.ta.ittpizen.feature_job.job

import androidx.paging.PagingData
import com.ta.ittpizen.domain.model.job.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class JobUiState(
    val jobLoaded: Boolean = false,
    val jobs: Flow<PagingData<Job>> = emptyFlow()
)
