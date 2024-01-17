package com.ta.ittpizen.feature_job.job

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ta.ittpizen.domain.usecase.IttpizenPagedUseCase
import com.ta.ittpizen.domain.usecase.IttpizenUseCase
import com.ta.ittpizen.domain.usecase.UserPreferenceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class JobViewModel(
    private val userPreferenceUseCase: UserPreferenceUseCase,
    private val ittpizenPagedUseCase: IttpizenPagedUseCase,
    private val ittpizenUseCase: IttpizenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(JobUiState())
    val uiState: StateFlow<JobUiState> get() = _uiState

    val userPreference get() = userPreferenceUseCase.userPreference

    fun getAllJob(token: String, workplaceType: String, jobType: String) {
        _uiState.update {
            it.copy(
                jobLoaded = true,
                jobs = ittpizenPagedUseCase.getAllJob(token = token, workplaceType, jobType).cachedIn(viewModelScope),
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