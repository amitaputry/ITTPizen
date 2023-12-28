package com.ta.ittpizen.feature_job.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddJobDetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AddJobDetailUiState())
    val uiState: StateFlow<AddJobDetailUiState> get() = _uiState

    val descriptionError get() = _uiState.map {
        it.descriptionErrorMessage.isNotEmpty()
    }

    val buttonPostDetailEnabled get() = _uiState.map {
        it.description.isNotEmpty() && it.experience.isNotEmpty() && it.graduate.isNotEmpty()
    }

    fun updateDescription(description: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(description = description)
            }
        }
    }
    fun updateSkills(skills: List<String>) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(skills = skills)
            }
        }
    }
    fun updateExperience(experience: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(experience = experience)
            }
        }
    }
    fun updateGraduate(graduates: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(graduate = graduates)
            }
        }
    }
    fun updateLinkApplication(linkApplication: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(linkApplication = linkApplication)
            }
        }
    }

    fun updateDescriptionErrorMessage(message: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(descriptionErrorMessage = message)
            }
        }
    }

    fun addSkill() {
        val mutableSkills = _uiState.value.skills.toMutableList()
        mutableSkills.add("")
        _uiState.update {
            it.copy(
                skills = mutableSkills
            )
        }
    }

}