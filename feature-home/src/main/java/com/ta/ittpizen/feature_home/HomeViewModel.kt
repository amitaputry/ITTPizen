package com.ta.ittpizen.feature_home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ta.ittpizen.domain.model.post.PostType
import com.ta.ittpizen.domain.usecase.IttpizenPagedUseCase
import com.ta.ittpizen.domain.usecase.IttpizenUseCase
import com.ta.ittpizen.domain.usecase.UserPreferenceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userPreferenceUseCase: UserPreferenceUseCase,
    private val ittpizenPagedUseCase: IttpizenPagedUseCase,
    private val ittpizenUseCase: IttpizenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> get() = _uiState

    val userPreference get() = userPreferenceUseCase.userPreference

    fun getAllPost(token: String) {
        _uiState.update {
            it.copy(
                allPostLoaded = true,
                allPost = ittpizenPagedUseCase.getAllPost(token = token, type = PostType.All).cachedIn(viewModelScope),
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

}