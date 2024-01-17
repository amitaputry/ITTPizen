package com.ta.ittpizen.feature_job.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.usecase.IttpizenUseCase
import com.ta.ittpizen.domain.usecase.UserPreferenceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class JobDetailViewModel(
    private val userPreferenceUseCase: UserPreferenceUseCase,
    private val useCase: IttpizenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(JobDetailUiState())
    val uiState: StateFlow<JobDetailUiState> get() = _uiState

    val userPreference get() = userPreferenceUseCase.userPreference

    val buttonEnabled get() = _uiState.map {
        it.detailJob is Resource.Success
    }

    val buttonSaveLoading get() = _uiState.map {
        it.detailJob is Resource.Loading
    }

    fun getJobById(token: String, jobId: String) {
        viewModelScope.launch {
            useCase.getJobById(token, jobId).collect { result ->
                _uiState.update {
                    it.copy(detailJob = result)
                }
            }
        }
    }

    fun saveJob(token: String, jobId: String) {
        viewModelScope.launch {
            useCase.saveJob(token, jobId).collect()
        }
    }

    fun unSaveJob(token: String, jobId: String) {
        viewModelScope.launch {
            useCase.unSaveJob(token, jobId).collect()
        }
    }

}