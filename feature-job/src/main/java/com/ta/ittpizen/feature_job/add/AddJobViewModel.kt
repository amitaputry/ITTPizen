package com.ta.ittpizen.feature_job.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ta.ittpizen.data.remote.response.job.CreateJobResult
import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.usecase.IttpizenUseCase
import com.ta.ittpizen.domain.usecase.UserPreferenceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddJobViewModel(
    private val userPreferenceUseCase: UserPreferenceUseCase,
    private val useCase: IttpizenUseCase
) : ViewModel() {

    private val _createJobResult = MutableStateFlow<Resource<CreateJobResult>>(Resource.Idle)
    val createJobResult: StateFlow<Resource<CreateJobResult>> get() = _createJobResult

    val buttonLoading get() = _createJobResult.map {
        it is Resource.Loading || it is Resource.Success
    }

    val userPreference get() = userPreferenceUseCase.userPreference

    fun createJob(
        token: String,
        title: String,
        company: String,
        street: String,
        city: String,
        province: String,
        workplaceType: String,
        jobType: String,
        description: String,
        skills: List<String> = emptyList(),
        experience: String,
        graduates: String,
        link: String
    ) {
        viewModelScope.launch {
            useCase.createJob(
                token,
                title,
                company,
                street,
                city,
                province,
                workplaceType,
                jobType,
                description,
                skills,
                experience,
                graduates,
                link
            ).collect { result ->
                _createJobResult.update { result }
            }
        }
    }

}