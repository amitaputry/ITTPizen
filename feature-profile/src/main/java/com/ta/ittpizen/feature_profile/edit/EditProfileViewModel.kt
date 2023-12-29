package com.ta.ittpizen.feature_profile.edit

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class EditProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(EditProfileUiState())
    val uiState: StateFlow<EditProfileUiState> get() = _uiState

    val buttonEnable get() = _uiState.map {
        it.displayName.isNotEmpty()
    }

    val displayNameError get() = _uiState.map {
        it.displayNameErrorName.isNotEmpty()
    }

    fun updateDisplayName(displayName: String) {
        _uiState.update {
            it.copy(displayName = displayName)
        }
    }

    fun updateBio(bio: String) {
        _uiState.update {
            it.copy(bio = bio)
        }
    }

    fun updateNameErrorMessage(message: String) {
        _uiState.update {
            it.copy(displayNameErrorName = message)
        }
    }

}