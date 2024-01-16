package com.ta.ittpizen.domain.repository

import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.model.auth.LoginResult
import com.ta.ittpizen.domain.model.auth.RegisterResult
import com.ta.ittpizen.domain.model.post.CreatePostCommentResult
import com.ta.ittpizen.domain.model.post.Post
import com.ta.ittpizen.domain.model.post.PostComment
import kotlinx.coroutines.flow.Flow

interface IttpizenRepository {

    fun login(email: String, password: String): Flow<Resource<LoginResult>>

    fun register(
        name: String,
        studentOrLectureId: String,
        email: String,
        phone: String,
        gender: String,
        type: String,
        password: String
    ): Flow<Resource<RegisterResult>>

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
    ): Flow<Resource<CreatePostCommentResult>>

    fun createPostLike(
        token: String,
        postId: String
    ): Flow<Resource<Boolean>>

    fun deletePostLike(
        token: String,
        postId: String
    ): Flow<Resource<Boolean>>

}