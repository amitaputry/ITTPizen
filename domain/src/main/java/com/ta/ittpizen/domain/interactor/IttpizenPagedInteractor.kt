package com.ta.ittpizen.domain.interactor

import androidx.paging.PagingData
import com.ta.ittpizen.domain.model.post.Post
import com.ta.ittpizen.domain.model.post.PostType
import com.ta.ittpizen.domain.repository.IttpizenPagedRepository
import com.ta.ittpizen.domain.usecase.IttpizenPagedUseCase
import kotlinx.coroutines.flow.Flow

class IttpizenPagedInteractor(
    private val repository: IttpizenPagedRepository
) : IttpizenPagedUseCase {
    override fun getAllPost(token: String, type: PostType): Flow<PagingData<Post>> {
        return repository.getAllPost(token, type)
    }

    override fun getPostByUser(token: String, userId: String): Flow<PagingData<Post>> {
        return repository.getPostByUser(token, userId)
    }
}