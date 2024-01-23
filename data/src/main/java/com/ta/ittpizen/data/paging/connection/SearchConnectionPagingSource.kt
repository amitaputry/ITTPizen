package com.ta.ittpizen.data.paging.connection

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.haroldadmin.cnradapter.NetworkResponse
import com.ta.ittpizen.data.mapper.connection.toDomains
import com.ta.ittpizen.data.remote.RemoteDataSource
import com.ta.ittpizen.domain.model.connection.Connection

class SearchConnectionPagingSource(
    private val token: String,
    private val query: String,
    private val remoteDataSource: RemoteDataSource
) : PagingSource<Int, Connection>() {

    companion object {
        const val DEFAULT_PAGE = 1
        const val DEFAULT_SIZE = 10
    }

    override fun getRefreshKey(state: PagingState<Int, Connection>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Connection> {
        return try {
            val page = params.key ?: DEFAULT_PAGE
            val size = params.loadSize
            val response = remoteDataSource.searchConnection(token, query, page, size)
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