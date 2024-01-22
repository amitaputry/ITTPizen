package com.ta.ittpizen.feature_post.add

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.model.post.CreatePostResult
import com.ta.ittpizen.domain.usecase.IttpizenUseCase
import com.ta.ittpizen.domain.usecase.UserPreferenceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File

class AddPostViewModel(
    private val userPreferenceUseCase: UserPreferenceUseCase,
    private val useCase: IttpizenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddPostUiState())
    val uiState: StateFlow<AddPostUiState> get() = _uiState

    private val _createPostResult = MutableStateFlow<Resource<CreatePostResult>>(Resource.Idle)
    val createPostResult: StateFlow<Resource<CreatePostResult>> get() = _createPostResult

    val userPreference get() = userPreferenceUseCase.userPreference

    val buttonEnabled get() = _uiState.map {
        it.text.isNotEmpty()
    }

    val buttonLoading get() = _createPostResult.map {
        it is Resource.Loading
    }

    fun updateMedia(media: Uri?) {
        _uiState.update { it.copy(media = media) }
    }

    fun updateText(text: String) {
        _uiState.update { it.copy(text = text) }
    }

    fun updateType(type: String) {
        _uiState.update { it.copy(type = type) }
    }

    fun createPost(token: String, media: File?) {
        val text = _uiState.value.text
        val type = _uiState.value.type
        viewModelScope.launch {
            useCase.createPost(token, media, text, type).collect { result ->
                _createPostResult.update { result }
            }
        }
    }

}