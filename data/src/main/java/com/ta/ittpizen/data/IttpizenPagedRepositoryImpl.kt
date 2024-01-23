package com.ta.ittpizen.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ta.ittpizen.data.paging.connection.ConnectionPagingSource
import com.ta.ittpizen.data.paging.connection.SearchConnectionPagingSource
import com.ta.ittpizen.data.paging.job.JobPagingSource
import com.ta.ittpizen.data.paging.job.SavedJobPagingSource
import com.ta.ittpizen.data.paging.job.SearchJobPagingSource
import com.ta.ittpizen.data.paging.post.PostByUserPagingSource
import com.ta.ittpizen.data.paging.post.PostPagingSource
import com.ta.ittpizen.data.remote.RemoteDataSource
import com.ta.ittpizen.domain.model.connection.Connection
import com.ta.ittpizen.domain.model.job.Job
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
        return Pager(
            config = PagingConfig(pageSize = PostByUserPagingSource.DEFAULT_SIZE),
            pagingSourceFactory = { PostByUserPagingSource(token, userId, remoteDataSource) }
        ).flow
    }

    override fun getAllConnection(token: String, type: String): Flow<PagingData<Connection>> {
        return Pager(
            config = PagingConfig(ConnectionPagingSource.DEFAULT_SIZE),
            pagingSourceFactory = { ConnectionPagingSource(token, type, remoteDataSource) }
        ).flow
    }

    override fun searchConnection(token: String, query: String): Flow<PagingData<Connection>> {
        return Pager(
            config = PagingConfig(SearchConnectionPagingSource.DEFAULT_SIZE),
            pagingSourceFactory = { SearchConnectionPagingSource(token, query, remoteDataSource) }
        ).flow
    }

    override fun getAllJob(
        token: String,
        workplaceType: String,
        jobType: String
    ): Flow<PagingData<Job>> {
        return Pager(
            config = PagingConfig(JobPagingSource.DEFAULT_SIZE),
            pagingSourceFactory = { JobPagingSource(token, workplaceType, jobType, remoteDataSource) }
        ).flow
    }

    override fun searchJob(token: String, query: String): Flow<PagingData<Job>> {
        return Pager(
            config = PagingConfig(SearchJobPagingSource.DEFAULT_SIZE),
            pagingSourceFactory = { SearchJobPagingSource(token, query, remoteDataSource) }
        ).flow
    }

    override fun getSavedJob(token: String): Flow<PagingData<Job>> {
        return Pager(
            config = PagingConfig(SavedJobPagingSource.DEFAULT_SIZE),
            pagingSourceFactory = { SavedJobPagingSource(token, remoteDataSource) }
        ).flow
    }
}