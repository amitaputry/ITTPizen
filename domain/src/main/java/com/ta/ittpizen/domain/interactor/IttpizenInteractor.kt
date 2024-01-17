package com.ta.ittpizen.domain.interactor

import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.model.auth.LoginResult
import com.ta.ittpizen.domain.model.auth.RegisterResult
import com.ta.ittpizen.domain.model.connection.DetailConnection
import com.ta.ittpizen.domain.model.post.CreatePostCommentResult
import com.ta.ittpizen.domain.model.post.CreatePostResult
import com.ta.ittpizen.domain.model.post.Post
import com.ta.ittpizen.domain.model.post.PostComment
import com.ta.ittpizen.domain.repository.IttpizenRepository
import com.ta.ittpizen.domain.usecase.IttpizenUseCase
import kotlinx.coroutines.flow.Flow
import java.io.File

class IttpizenInteractor(
    private val repository: IttpizenRepository
) : IttpizenUseCase {

    override fun login(email: String, password: String): Flow<Resource<LoginResult>> {
        return repository.login(email, password)
    }

    override fun register(
        name: String,
        studentOrLectureId: String,
        email: String,
        phone: String,
        gender: String,
        type: String,
        password: String
    ): Flow<Resource<RegisterResult>> {
        return repository.register(name, studentOrLectureId, email, phone, gender, type, password)
    }

    override fun createPost(
        token: String,
        media: File?,
        text: String,
        type: String
    ): Flow<Resource<CreatePostResult>> {
        return repository.createPost(token, media, text, type)
    }

    override fun getPostById(token: String, postId: String): Flow<Resource<Post>> {
        return repository.getPostById(token, postId)
    }

    override fun getPostComment(token: String, postId: String): Flow<Resource<List<PostComment>>> {
        return repository.getPostComment(token, postId)
    }

    override fun createPostComment(
        token: String,
        postId: String,
        comment: String
    ): Flow<Resource<CreatePostCommentResult>> {
        return repository.createPostComment(token, postId, comment)
    }

    override fun createPostLike(token: String, postId: String): Flow<Resource<Boolean>> {
        return repository.createPostLike(token, postId)
    }

    override fun deletePostLike(token: String, postId: String): Flow<Resource<Boolean>> {
        return repository.deletePostLike(token, postId)
    }

    override fun getConnectionById(
        token: String,
        userId: String
    ): Flow<Resource<DetailConnection>> {
        return repository.getConnectionById(token, userId)
    }
}