package com.ta.ittpizen.data.remote

import com.haroldadmin.cnradapter.NetworkResponse
import com.ta.ittpizen.data.remote.request.auth.LoginRequest
import com.ta.ittpizen.data.remote.request.auth.RegisterRequest
import com.ta.ittpizen.data.remote.request.job.CreateJobRequest
import com.ta.ittpizen.data.remote.request.post.CreatePostCommentRequest
import com.ta.ittpizen.data.remote.response.CommonErrorResponse
import com.ta.ittpizen.data.remote.response.CommonResponse
import com.ta.ittpizen.data.remote.response.PagedCommonResponse
import com.ta.ittpizen.data.remote.response.auth.LoginResponse
import com.ta.ittpizen.data.remote.response.auth.RegisterResponse
import com.ta.ittpizen.data.remote.response.connection.ConnectionResponse
import com.ta.ittpizen.data.remote.response.connection.CreateConnectionResponse
import com.ta.ittpizen.data.remote.response.connection.DetailConnectionResponse
import com.ta.ittpizen.data.remote.response.job.CreateJobResponse
import com.ta.ittpizen.data.remote.response.job.DetailJobResponse
import com.ta.ittpizen.data.remote.response.job.JobResponse
import com.ta.ittpizen.data.remote.response.job.SaveJobResponse
import com.ta.ittpizen.data.remote.response.post.CreatePostCommentResponse
import com.ta.ittpizen.data.remote.response.post.CreatePostResponse
import com.ta.ittpizen.data.remote.response.post.PostCommentResponse
import com.ta.ittpizen.data.remote.response.post.PostResponse
import com.ta.ittpizen.data.remote.service.IttpizenService
import okhttp3.MultipartBody
import okhttp3.RequestBody

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
        userId: String,
        page: Int,
        size: Int
    ): NetworkResponse<PagedCommonResponse<List<PostResponse>>, CommonErrorResponse> {
        val authorization = "Bearer $token"
        return service.getPostByUser(authorization, userId, page, size)
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
        val request = CreatePostCommentRequest(comment)
        return service.createPostComment(authorization, postId, request)
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

    suspend fun getConnectionById(
        token: String,
        userId: String
    ): NetworkResponse<CommonResponse<DetailConnectionResponse>, CommonErrorResponse> {
        val authorization = "Bearer $token"
        return service.getConnectionById(authorization, userId)
    }

    suspend fun createConnection(
        token: String,
        userId: String
    ): NetworkResponse<CommonResponse<CreateConnectionResponse>, CommonErrorResponse> {
        val authorization = "Bearer $token"
        return service.createConnection(authorization, userId)
    }

    suspend fun deleteConnection(
        token: String,
        userId: String
    ): NetworkResponse<CommonResponse<String?>, CommonErrorResponse> {
        val authorization = "Bearer $token"
        return service.deleteConnection(authorization, userId)
    }

    suspend fun createJob(
        token: String,
        title: String,
        company: String,
        street: String,
        city: String,
        province: String,
        workplaceType: String,
        jobType: String,
        description: String,
        skills: List<String>,
        experience: String,
        graduates: String,
        link: String
    ): NetworkResponse<CommonResponse<CreateJobResponse>, CommonErrorResponse> {
        val authorization = "Bearer $token"
        val request = CreateJobRequest(skills, province, city, graduates, street, link, description, company, workplaceType, title, jobType, experience)
        return service.createJob(authorization, request)
    }

    suspend fun getAllJob(
        token: String,
        workplaceType: String,
        jobType: String,
        page: Int,
        size: Int
    ): NetworkResponse<PagedCommonResponse<List<JobResponse>>, CommonErrorResponse> {
        val authorization = "Bearer $token"
        return service.getAllJob(authorization, workplaceType, jobType, page, size)
    }

    suspend fun getSavedJob(
        token: String,
        page: Int,
        size: Int
    ): NetworkResponse<PagedCommonResponse<List<JobResponse>>, CommonErrorResponse> {
        val authorization = "Bearer $token"
        return service.getSavedJob(authorization, page, size)
    }

    suspend fun saveJob(
        token: String,
        jobId: String,
    ): NetworkResponse<CommonResponse<SaveJobResponse>, CommonErrorResponse> {
        val authorization = "Bearer $token"
        return service.saveJob(authorization, jobId)
    }

    suspend fun unSaveJob(
        token: String,
        jobId: String,
    ): NetworkResponse<CommonResponse<Unit>, CommonErrorResponse> {
        val authorization = "Bearer $token"
        return service.unSaveJob(authorization, jobId)
    }

    suspend fun getJobById(
        token: String,
        jobId: String
    ): NetworkResponse<CommonResponse<DetailJobResponse>, CommonErrorResponse> {
        val authorization = "Bearer $token"
        return service.getJobById(authorization, jobId)
    }

}