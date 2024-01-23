package com.ta.ittpizen.domain.usecase

import androidx.paging.PagingData
import com.ta.ittpizen.domain.model.connection.Connection
import com.ta.ittpizen.domain.model.job.Job
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

    fun getAllJob(
        token: String,
        workplaceType: String,
        jobType: String,
    ): Flow<PagingData<Job>>

    fun getSavedJob(
        token: String
    ): Flow<PagingData<Job>>

}