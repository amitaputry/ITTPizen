package com.ta.ittpizen.data.paging.post

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.haroldadmin.cnradapter.NetworkResponse
import com.ta.ittpizen.data.mapper.post.toDomains
import com.ta.ittpizen.data.remote.RemoteDataSource
import com.ta.ittpizen.domain.model.post.Post

class PostByUserPagingSource(
    private val token: String,
    private val userId: String,
    private val remoteDataSource: RemoteDataSource
) : PagingSource<Int, Post>() {

    companion object {
        const val DEFAULT_PAGE = 1
        const val DEFAULT_SIZE = 10
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val page = params.key ?: DEFAULT_PAGE
            val size = params.loadSize
            val response = remoteDataSource.getPostByUser(token, userId, page, size)
            when (response) {
                is NetworkResponse.Success -> {
                    val data = response.body.data.toDomains()
                    val nextKey = response.body.nextPage
                    val prevKey = response.body.prevPage
                    LoadResult.Page(
                        data = data,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }
                is NetworkResponse.Error -> {
                    val message = response.body?.message ?: response.error?.message
                    LoadResult.Error(
                        throwable = Throwable(message)
                    )
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}