package com.ta.ittpizen.feature_profile.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.model.preference.UserPreference
import com.ta.ittpizen.domain.model.profile.Profile
import com.ta.ittpizen.domain.usecase.IttpizenUseCase
import com.ta.ittpizen.domain.usecase.UserPreferenceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File

class EditProfileViewModel(
    private val userPreferenceUseCase: UserPreferenceUseCase,
    private val useCase: IttpizenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditProfileUiState())
    val uiState: StateFlow<EditProfileUiState> get() = _uiState

    private val _updateProfileResult = MutableStateFlow<Resource<Profile>>(Resource.Idle)
    val updateProfileResult: StateFlow<Resource<Profile>> get() = _updateProfileResult

    val buttonEnabled get() = _uiState.map {
        it.displayName.isNotEmpty()
    }

    val buttonLoading get() = _updateProfileResult.map {
        it is Resource.Loading
    }

    val displayNameError get() = _uiState.map {
        it.displayNameErrorName.isNotEmpty()
    }

    private val _profile = MutableStateFlow<Resource<Profile>>(Resource.Loading)
    val profile: StateFlow<Resource<Profile>> get() = _profile

    val userPreference get() = userPreferenceUseCase.userPreference

    fun getProfile(token: String) {
        viewModelScope.launch {
            useCase.getProfile(token).collect {
                _profile.value = it
            }
        }
    }

    fun updatePhoto(photo: String) {
        _uiState.update {
            it.copy(photo = photo)
        }
    }

    fun updateName(name: String) {
        _uiState.update {
            it.copy(name = name)
        }
    }

    fun updateType(type: String) {
        _uiState.update {
            it.copy(type = type)
        }
    }

    fun updateStudentOrLectureId(studentOrLectureId: String) {
        _uiState.update {
            it.copy(studentOrLectureId = studentOrLectureId)
        }
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

    fun updateProfile(token: String, file: File?) {
        val name = _uiState.value.displayName
        val bio = _uiState.value.bio
        viewModelScope.launch {
            useCase.updateProfile(token, name, bio, file).collect { result ->
                _updateProfileResult.value = result
            }
        }
    }

    fun updateUserPreference(userPreference: UserPreference) {
        viewModelScope.launch {
            userPreferenceUseCase.updateUserPreference(userPreference)
        }
    }

}