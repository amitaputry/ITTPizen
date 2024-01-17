package com.ta.ittpizen.feature_job.detail

import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.model.job.DetailJobResult

data class JobDetailUiState(
    val detailJob: Resource<DetailJobResult> = Resource.Loading
)
