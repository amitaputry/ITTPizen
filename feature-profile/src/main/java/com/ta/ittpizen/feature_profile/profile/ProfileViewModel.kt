package com.ta.ittpizen.feature_profile.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ta.ittpizen.domain.model.preference.UserPreference
import com.ta.ittpizen.domain.usecase.IttpizenPagedUseCase
import com.ta.ittpizen.domain.usecase.IttpizenUseCase
import com.ta.ittpizen.domain.usecase.SettingPreferenceUseCase
import com.ta.ittpizen.domain.usecase.UserPreferenceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userPreferenceUseCase: UserPreferenceUseCase,
    private val ittpizenPagedUseCase: IttpizenPagedUseCase,
    private val ittpizenUseCase: IttpizenUseCase,
    private val settingPreferenceUseCase: SettingPreferenceUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> get() = _uiState

    val userPreference get() = userPreferenceUseCase.userPreference

    fun getProfile(token: String, userId: String) {
        viewModelScope.launch {
            ittpizenUseCase.getConnectionById(token, userId).collect { result ->
                _uiState.update { it.copy(profile = result) }
            }
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

    fun getAllPost(token: String, userId: String) {
        _uiState.update {
            it.copy(
                allPostLoaded = true,
                allPost = ittpizenPagedUseCase.getPostByUser(token = token, userId = userId).cachedIn(viewModelScope),
            )
        }
    }

    fun createPostLike(token: String, postId: String) {
        viewModelScope.launch {
            ittpizenUseCase.createPostLike(token, postId).collect()
        }
    }

    fun deletePostLike(token: String, postId: String) {
        viewModelScope.launch {
            ittpizenUseCase.deletePostLike(token, postId).collect()
        }
    }

    fun logout() {
        viewModelScope.launch {
            settingPreferenceUseCase.updateIsLoginState(false)
            userPreferenceUseCase.updateUserPreference(UserPreference())
        }
    }

}