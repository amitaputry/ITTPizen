package com.ta.ittpizen.data

import com.haroldadmin.cnradapter.NetworkResponse
import com.ta.ittpizen.data.mapper.auth.toDomain
import com.ta.ittpizen.data.remote.RemoteDataSource
import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.model.auth.LoginResult
import com.ta.ittpizen.domain.model.auth.RegisterResult
import com.ta.ittpizen.domain.model.post.CreatePostCommentResult
import com.ta.ittpizen.domain.model.post.Post
import com.ta.ittpizen.domain.model.post.PostComment
import com.ta.ittpizen.domain.repository.IttpizenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class IttpizenRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : IttpizenRepository {

    override fun login(email: String, password: String): Flow<Resource<LoginResult>> = flow {
        emit(Resource.Loading)
        when (val response = remoteDataSource.login(email, password)) {
            is NetworkResponse.Success -> {
                val result = response.body.toDomain()
                emit(Resource.Success(result))
            }
            is NetworkResponse.Error -> {
                val message = response.body?.data ?: response.error?.message
                emit(Resource.Error(message = message))
            }

        }
    }.catch {
        emit(Resource.Error(message = it.message))
    }

    override fun register(
        name: String,
        studentOrLectureId: String,
        email: String,
        phone: String,
        gender: String,
        type: String,
        password: String
    ): Flow<Resource<RegisterResult>> = flow {
        emit(Resource.Loading)
        val response = remoteDataSource.register(
            name,
            studentOrLectureId,
            email,
            phone,
            gender,
            type,
            password
        )
        when (response) {
            is NetworkResponse.Success -> {
                val result = response.body.toDomain()
                emit(Resource.Success(result))
            }
            is NetworkResponse.Error -> {
                val message = response.body?.data ?: response.error?.message
                emit(Resource.Error(message = message))
            }

        }
    }.catch {
        emit(Resource.Error(message = it.message))
    }

    override fun getPostById(token: String, postId: String): Flow<Resource<Post>> = flow {

    }

    override fun getPostComment(token: String, postId: String): Flow<Resource<List<PostComment>>> = flow {

    }

    override fun createPostComment(
        token: String,
        postId: String,
        comment: String
    ): Flow<Resource<CreatePostCommentResult>> = flow {

    }

    override fun createPostLike(token: String, postId: String): Flow<Resource<Boolean>> = flow {

    }

    override fun deletePostLike(token: String, postId: String): Flow<Resource<Boolean>> = flow {

    }
}