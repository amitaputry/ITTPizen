package com.ta.ittpizen.feature_home

import androidx.paging.PagingData
import com.ta.ittpizen.domain.model.post.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeUiState(
    val allPostLoaded: Boolean = false,
    val allPost: Flow<PagingData<Post>> = emptyFlow()
)
