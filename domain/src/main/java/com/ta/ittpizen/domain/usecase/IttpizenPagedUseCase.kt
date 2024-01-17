package com.ta.ittpizen.domain.usecase

import androidx.paging.PagingData
import com.ta.ittpizen.domain.model.connection.Connection
import com.ta.ittpizen.domain.model.post.Post
import com.ta.ittpizen.domain.model.post.PostType
import kotlinx.coroutines.flow.Flow

interface IttpizenPagedUseCase {

    fun getAllPost(
        token: String,
        type: PostType
    ): Flow<PagingData<Post>>

    fun getPostByUser(
        token: String,
        userId: String
    ): Flow<PagingData<Post>>

    fun getAllConnection(
        token: String,
        type: String
    ): Flow<PagingData<Connection>>

}