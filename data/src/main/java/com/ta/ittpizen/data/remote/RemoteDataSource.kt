package com.ta.ittpizen.data.remote

import com.haroldadmin.cnradapter.NetworkResponse
import com.ta.ittpizen.data.remote.request.auth.LoginRequest
import com.ta.ittpizen.data.remote.request.auth.RegisterRequest
import com.ta.ittpizen.data.remote.response.CommonErrorResponse
import com.ta.ittpizen.data.remote.response.CommonResponse
import com.ta.ittpizen.data.remote.response.PagedCommonResponse
import com.ta.ittpizen.data.remote.response.auth.LoginResponse
import com.ta.ittpizen.data.remote.response.auth.RegisterResponse
import com.ta.ittpizen.data.remote.response.connection.ConnectionResponse
import com.ta.ittpizen.data.remote.response.post.CreatePostCommentResponse
import com.ta.ittpizen.data.remote.response.post.CreatePostResponse
import com.ta.ittpizen.data.remote.response.post.PostCommentResponse
import com.ta.ittpizen.data.remote.response.post.PostResponse
import com.ta.ittpizen.data.remote.service.IttpizenService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Header
import retrofit2.http.Query

class RemoteDataSource(private val service: IttpizenService) {

    suspend fun login(
        email: String,
        password: String
    ): NetworkResponse<CommonResponse<LoginResponse>, CommonErrorResponse> {
        val request = LoginRequest(email, password)
        return service.login(request)
    }

    suspend fun register(
        name: String,
        studentOrLectureId: String,
        email: String,
        phone: String,
        gender: String,
        type: String,
        password: String
    ): NetworkResponse<CommonResponse<RegisterResponse>, CommonErrorResponse> {
        val request = RegisterRequest(name, studentOrLectureId, email, phone, gender, type, password)
        return service.register(request)
    }

    suspend fun createPost(
        token: String,
        type: RequestBody,
        text: RequestBody,
        media: MultipartBody.Part? = null
    ): NetworkResponse<CommonResponse<CreatePostResponse>, CommonErrorResponse> {
        val authorization = "Bearer $token"
        return service.createPost(authorization, type, text, media)
    }

    suspend fun getAllPost(
        token: String,
        type: String,
        page: Int,
        size: Int
    ): NetworkResponse<PagedCommonResponse<List<PostResponse>>, CommonErrorResponse> {
        val authorization = "Bearer $token"
        return service.getAllPost(authorization, type, page, size)
    }

    suspend fun getPostByUser(
        token: String,
        userId: String
    ): NetworkResponse<PagedCommonResponse<List<PostResponse>>, CommonErrorResponse> {
        val authorization = "Bearer $token"
        return service.getPostByUser(authorization, userId)
    }

    suspend fun getPostById(
        token: String,
        postId: String
    ): NetworkResponse<CommonResponse<PostResponse>, CommonErrorResponse> {
        val authorization = "Bearer $token"
        return service.getPostById(authorization, postId)
    }

    suspend fun getPostComment(
        token: String,
        postId: String
    ): NetworkResponse<CommonResponse<List<PostCommentResponse>>, CommonErrorResponse> {
        val authorization = "Bearer $token"
        return service.getPostComment(authorization, postId)
    }

    suspend fun createPostComment(
        token: String,
        postId: String,
        comment: String
    ): NetworkResponse<CommonResponse<CreatePostCommentResponse>, CommonErrorResponse> {
        val authorization = "Bearer $token"
        return service.createPostComment(authorization, postId, comment)
    }

    suspend fun createPostLike(
        token: String,
        postId: String,
    ): NetworkResponse<CommonResponse<String>, CommonErrorResponse> {
        val authorization = "Bearer $token"
        return service.createPostLike(authorization, postId)
    }

    suspend fun deletePostLike(
        token: String,
        postId: String,
    ): NetworkResponse<CommonResponse<String>, CommonErrorResponse> {
        val authorization = "Bearer $token"
        return service.deletePostLike(authorization, postId)
    }

    suspend fun getAllConnection(
        token: String,
        type: String,
        page: Int,
        size: Int
    ): NetworkResponse<PagedCommonResponse<List<ConnectionResponse>>, CommonErrorResponse> {
        val authorization = "Bearer $token"
        return service.getAllConnection(authorization, type, page, size)
    }

}