@file:OptIn(ExperimentalCoroutinesApi::class)

package com.ta.ittpizen.feature_job.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ta.ittpizen.domain.usecase.IttpizenPagedUseCase
import com.ta.ittpizen.domain.usecase.IttpizenUseCase
import com.ta.ittpizen.domain.usecase.UserPreferenceUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SavedJobViewModel(
    private val userPreferenceUseCase: UserPreferenceUseCase,
    private val ittpizenPagedUseCase: IttpizenPagedUseCase,
    private val ittpizenUseCase: IttpizenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SavedJobUiState())
    val uiState: StateFlow<SavedJobUiState> get() = _uiState

    val userPreference get() = userPreferenceUseCase.userPreference

    fun getSavedJob(token: String) {
        _uiState.update {
            it.copy(
                jobLoaded = true,
                jobs = ittpizenPagedUseCase.getSavedJob(token = token).cachedIn(viewModelScope),
            )
        }
    }

    fun unSaveJob(token: String, jobId: String) {
        viewModelScope.launch {
            ittpizenUseCase.unSaveJob(token, jobId).collect()
        }
    }

}