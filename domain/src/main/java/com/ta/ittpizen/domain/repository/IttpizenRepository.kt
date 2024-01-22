package com.ta.ittpizen.domain.repository

import com.ta.ittpizen.data.remote.response.job.CreateJobResult
import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.model.auth.LoginResult
import com.ta.ittpizen.domain.model.auth.RegisterResult
import com.ta.ittpizen.domain.model.connection.DetailConnection
import com.ta.ittpizen.domain.model.job.DetailJobResult
import com.ta.ittpizen.domain.model.post.CreatePostResult
import com.ta.ittpizen.domain.model.post.Post
import com.ta.ittpizen.domain.model.post.PostComment
import kotlinx.coroutines.flow.Flow
import java.io.File

interface IttpizenRepository {

    fun login(
        email: String,
        password: String
    ): Flow<Resource<LoginResult>>

    fun register(
        name: String,
        studentOrLectureId: String,
        email: String,
        phone: String,
        gender: String,
        type: String,
        password: String
    ): Flow<Resource<RegisterResult>>

    fun createPost(
        token: String,
        media: File?,
        text: String,
        type: String
    ): Flow<Resource<CreatePostResult>>

    fun getPostById(
        token: String,
        postId: String
    ): Flow<Resource<Post>>

    fun getPostComment(
        token: String,
        postId: String
    ): Flow<Resource<List<PostComment>>>

    fun createPostComment(
        token: String,
        postId: String,
        comment: String
    ): Flow<Resource<Boolean>>

    fun createPostLike(
        token: String,
        postId: String
    ): Flow<Resource<Boolean>>

    fun deletePostLike(
        token: String,
        postId: String
    ): Flow<Resource<Boolean>>

    fun getConnectionById(
        token: String,
        userId: String
    ): Flow<Resource<DetailConnection>>

    fun createJob(
        token: String,
        title: String,
        company: String,
        street: String,
        city: String,
        province: String,
        workplaceType: String,
        jobType: String,
        description: String,
        skills: List<String> = emptyList(),
        experience: String,
        graduates: String,
        link: String,
    ): Flow<Resource<CreateJobResult>>

    fun getJobById(
        token: String,
        jobId: String
    ): Flow<Resource<DetailJobResult>>

    fun saveJob(
        token: String,
        jobId: String
    ): Flow<Resource<Boolean>>

    fun unSaveJob(
        token: String,
        jobId: String
    ): Flow<Resource<Boolean>>

}