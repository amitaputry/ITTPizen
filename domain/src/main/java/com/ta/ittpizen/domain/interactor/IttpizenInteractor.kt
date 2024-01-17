package com.ta.ittpizen.domain.interactor

import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.model.auth.LoginResult
import com.ta.ittpizen.domain.model.auth.RegisterResult
import com.ta.ittpizen.domain.model.post.CreatePostCommentResult
import com.ta.ittpizen.domain.model.post.CreatePostResult
import com.ta.ittpizen.domain.model.post.Post
import com.ta.ittpizen.domain.model.post.PostComment
import com.ta.ittpizen.domain.repository.IttpizenRepository
import com.ta.ittpizen.domain.usecase.IttpizenUseCase
import kotlinx.coroutines.flow.Flow
import java.io.File

class IttpizenInteractor(
    private val ittpizenRepository: IttpizenRepository
) : IttpizenUseCase {

    override fun login(email: String, password: String): Flow<Resource<LoginResult>> {
        return ittpizenRepository.login(email, password)
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
        return ittpizenRepository.register(name, studentOrLectureId, email, phone, gender, type, password)
    }

    override fun createPost(
        token: String,
        media: File?,
        text: String,
        type: String
    ): Flow<Resource<CreatePostResult>> {
        return ittpizenRepository.createPost(token, media, text, type)
    }

    override fun getPostById(token: String, postId: String): Flow<Resource<Post>> {
        return ittpizenRepository.getPostById(token, postId)
    }

    override fun getPostComment(token: String, postId: String): Flow<Resource<List<PostComment>>> {
        return ittpizenRepository.getPostComment(token, postId)
    }

    override fun createPostComment(
        token: String,
        postId: String,
        comment: String
    ): Flow<Resource<CreatePostCommentResult>> {
        return ittpizenRepository.createPostComment(token, postId, comment)
    }

    override fun createPostLike(token: String, postId: String): Flow<Resource<Boolean>> {
        return ittpizenRepository.createPostLike(token, postId)
    }

    override fun deletePostLike(token: String, postId: String): Flow<Resource<Boolean>> {
        return ittpizenRepository.deletePostLike(token, postId)
    }
}