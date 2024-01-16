package com.ta.ittpizen.domain.interactor

import androidx.paging.PagingData
import com.ta.ittpizen.domain.model.post.Post
import com.ta.ittpizen.domain.model.post.PostType
import com.ta.ittpizen.domain.usecase.PagedIttpizenUseCase
import kotlinx.coroutines.flow.Flow

class PagedIttpizenInteractor(
    private val pagedIttpizenUseCase: PagedIttpizenUseCase
) : PagedIttpizenUseCase {
    override fun getAllPost(token: String, type: PostType): Flow<PagingData<Post>> {
        return pagedIttpizenUseCase.getAllPost(token, type)
    }

    override fun getPostByUser(token: String, userId: String): Flow<PagingData<Post>> {
        return pagedIttpizenUseCase.getPostByUser(token, userId)
    }
}