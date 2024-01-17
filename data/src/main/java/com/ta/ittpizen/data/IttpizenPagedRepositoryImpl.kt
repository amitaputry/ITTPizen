package com.ta.ittpizen.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ta.ittpizen.data.paging.ConnectionPagingSource
import com.ta.ittpizen.data.paging.PostPagingSource
import com.ta.ittpizen.data.remote.RemoteDataSource
import com.ta.ittpizen.domain.model.connection.Connection
import com.ta.ittpizen.domain.model.post.Post
import com.ta.ittpizen.domain.model.post.PostType
import com.ta.ittpizen.domain.repository.IttpizenPagedRepository
import kotlinx.coroutines.flow.Flow

class IttpizenPagedRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : IttpizenPagedRepository {

    override fun getAllPost(token: String, type: PostType): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(pageSize = PostPagingSource.DEFAULT_SIZE),
            pagingSourceFactory = { PostPagingSource(token, type.name, remoteDataSource) }
        ).flow
    }

    override fun getPostByUser(token: String, userId: String): Flow<PagingData<Post>> {
        TODO("Not yet implemented")
    }

    override fun getAllConnection(token: String, type: String): Flow<PagingData<Connection>> {
        return Pager(
            config = PagingConfig(ConnectionPagingSource.DEFAULT_PAGE),
            pagingSourceFactory = { ConnectionPagingSource(token, type, remoteDataSource) }
        ).flow
    }
}