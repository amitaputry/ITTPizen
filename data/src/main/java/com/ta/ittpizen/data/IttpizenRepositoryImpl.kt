package com.ta.ittpizen.data

import com.haroldadmin.cnradapter.NetworkResponse
import com.ta.ittpizen.common.reduceFileImage
import com.ta.ittpizen.data.mapper.auth.toDomain
import com.ta.ittpizen.data.mapper.connection.toDomain
import com.ta.ittpizen.data.mapper.post.toDomain
import com.ta.ittpizen.data.remote.RemoteDataSource
import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.model.auth.LoginResult
import com.ta.ittpizen.domain.model.auth.RegisterResult
import com.ta.ittpizen.domain.model.connection.DetailConnection
import com.ta.ittpizen.domain.model.post.CreatePostCommentResult
import com.ta.ittpizen.domain.model.post.CreatePostResult
import com.ta.ittpizen.domain.model.post.Post
import com.ta.ittpizen.domain.model.post.PostComment
import com.ta.ittpizen.domain.repository.IttpizenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class IttpizenRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : IttpizenRepository {

    override fun login(email: String, password: String): Flow<Resource<LoginResult>> = flow {
        emit(Resource.Loading)
        when (val response = remoteDataSource.login(email, password)) {
            is NetworkResponse.Success -> {
                val result = response.body.data.toDomain()
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
                val result = response.body.data.toDomain()
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

    override fun createPost(
        token: String,
        media: File?,
        text: String,
        type: String
    ): Flow<Resource<CreatePostResult>> = flow {
        emit(Resource.Loading)

        val compressedFile = withContext(Dispatchers.IO) { media?.reduceFileImage() }
        val textRequestBody = text.toRequestBody("text/plain".toMediaType())
        val typeRequestBody = type.toRequestBody("text/plain".toMediaType())
        val mediaRequestBody = compressedFile?.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val mediaMultipart = mediaRequestBody?.let {
            MultipartBody.Part.createFormData(
                "media",
                filename = compressedFile.name,
                body = mediaRequestBody
            )
        }

        when (val response = remoteDataSource.createPost(token, typeRequestBody, textRequestBody, mediaMultipart)) {
            is NetworkResponse.Success -> {
                val result = response.body.data.toDomain()
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
        emit(Resource.Loading)
        when (val response = remoteDataSource.createPostLike(token, postId)) {
            is NetworkResponse.Success -> emit(Resource.Success(true))
            is NetworkResponse.Error -> {
                val message = response.body?.data ?: response.error?.message
                emit(Resource.Error(message = message))
            }
        }
    }.catch {
        emit(Resource.Error(message = it.message))
    }

    override fun deletePostLike(token: String, postId: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        when (val response = remoteDataSource.deletePostLike(token, postId)) {
            is NetworkResponse.Success -> emit(Resource.Success(true))
            is NetworkResponse.Error -> {
                val message = response.body?.data ?: response.error?.message
                emit(Resource.Error(message = message))
            }
        }
    }.catch {
        emit(Resource.Error(message = it.message))
    }

    override fun getConnectionById(
        token: String,
        userId: String
    ): Flow<Resource<DetailConnection>> = flow {
        emit(Resource.Loading)
        when (val response = remoteDataSource.getConnectionById(token, userId)) {
            is NetworkResponse.Success -> {
                val result = response.body.data.toDomain()
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

    override fun saveJob(token: String, jobId: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        when (val response = remoteDataSource.saveJob(token, jobId)) {
            is NetworkResponse.Success -> emit(Resource.Success(true))
            is NetworkResponse.Error -> {
                val message = response.body?.data ?: response.error?.message
                emit(Resource.Error(message = message))
            }
        }
    }.catch {
        emit(Resource.Error(message = it.message))
    }

    override fun unSaveJob(token: String, jobId: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        when (val response = remoteDataSource.unSaveJob(token, jobId)) {
            is NetworkResponse.Success -> emit(Resource.Success(true))
            is NetworkResponse.Error -> {
                val message = response.body?.data ?: response.error?.message
                emit(Resource.Error(message = message))
            }
        }
    }.catch {
        emit(Resource.Error(message = it.message))
    }

}