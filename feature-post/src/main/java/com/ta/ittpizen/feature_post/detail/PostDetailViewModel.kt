package com.ta.ittpizen.feature_post.detail

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

class PostDetailViewModel(
    private val userPreferenceUseCase: UserPreferenceUseCase,
    private val useCase: IttpizenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PostDetailUiState())
    val uiState: StateFlow<PostDetailUiState> get() = _uiState

    val userPreference get() = userPreferenceUseCase.userPreference

    val buttonEnabled get() = _uiState.map {
        it.post is Resource.Success
    }

    fun getPostById(token: String, postId: String) {
        viewModelScope.launch {
            useCase.getPostById(token, postId).collect { result ->
                _uiState.update {
                    it.copy(post = result)
                }
            }
        }
    }

    fun getPostComment(token: String, postId: String) {
        viewModelScope.launch {
            useCase.getPostComment(token, postId).collect { result ->
                _uiState.update {
                    it.copy(postComment = result)
                }
            }
        }
    }

    fun createPostLike(token: String, postId: String) {
        viewModelScope.launch {
            useCase.createPostLike(token, postId).collect()
        }
    }

    fun deletePostLike(token: String, postId: String) {
        viewModelScope.launch {
            useCase.deletePostLike(token, postId).collect()
        }
    }

}