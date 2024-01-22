package com.ta.ittpizen.feature_connection.connection

import androidx.paging.PagingData
import com.ta.ittpizen.domain.model.connection.Connection
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ConnectionUiState(
    val connectionLoaded: Boolean = false,
    val connections: Flow<PagingData<Connection>> = emptyFlow()
)
