package com.ta.ittpizen.feature_connection.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ta.ittpizen.domain.usecase.IttpizenPagedUseCase
import com.ta.ittpizen.domain.usecase.IttpizenUseCase
import com.ta.ittpizen.domain.usecase.UserPreferenceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchConnectionViewModel(
    private val userPreferenceUseCase: UserPreferenceUseCase,
    private val ittpizenPagedUseCase: IttpizenPagedUseCase,
    private val ittpizenUseCase: IttpizenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchConnectionUiState())
    val uiState: StateFlow<SearchConnectionUiState> get() = _uiState

    val userPreference get() = userPreferenceUseCase.userPreference

    fun updateQuery(query: String) {
        _uiState.update {
            it.copy(query = query)
        }
    }

    fun searchConnection(token: String, query: String) {
        _uiState.update {
            it.copy(
                connectionLoaded = true,
                connections = ittpizenPagedUseCase.searchConnection(token = token, query = query),
            )
        }
    }

    fun createConnection(
        token: String,
        userId: String
    ) {
        viewModelScope.launch {
            ittpizenUseCase.createConnection(token, userId).collect()
        }
    }

    fun deleteConnection(
        token: String,
        userId: String
    ) {
        viewModelScope.launch {
            ittpizenUseCase.deleteConnection(token, userId).collect()
        }
    }

}