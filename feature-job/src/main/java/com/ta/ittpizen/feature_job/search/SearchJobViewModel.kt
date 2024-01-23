package com.ta.ittpizen.feature_job.search

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

class SearchJobViewModel(
    private val userPreferenceUseCase: UserPreferenceUseCase,
    private val ittpizenPagedUseCase: IttpizenPagedUseCase,
    private val ittpizenUseCase: IttpizenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchJobUiState())
    val uiState: StateFlow<SearchJobUiState> get() = _uiState

    val userPreference get() = userPreferenceUseCase.userPreference

    fun updateQuery(query: String) {
        _uiState.update {
            it.copy(query = query)
        }
    }

    fun searchJob(token: String, query: String) {
        _uiState.update {
            it.copy(
                jobLoaded = true,
                jobs = ittpizenPagedUseCase.searchJob(token = token, query = query),
            )
        }
    }

    fun saveJob(token: String, jobId: String) {
        viewModelScope.launch {
            ittpizenUseCase.saveJob(token, jobId).collect()
        }
    }

    fun unSaveJob(token: String, jobId: String) {
        viewModelScope.launch {
            ittpizenUseCase.unSaveJob(token, jobId).collect()
        }
    }

}