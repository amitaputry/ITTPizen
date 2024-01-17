package com.ta.ittpizen.feature_profile.profile

import androidx.paging.PagingData
import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.model.connection.DetailConnection
import com.ta.ittpizen.domain.model.post.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ProfileUiState(
    val profile: Resource<DetailConnection> = Resource.Loading,
    val allPostLoaded: Boolean = false,
    val allPost: Flow<PagingData<Post>> = emptyFlow()
)
