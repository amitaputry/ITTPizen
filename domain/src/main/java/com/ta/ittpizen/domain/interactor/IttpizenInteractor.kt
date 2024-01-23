package com.ta.ittpizen.domain.interactor

import com.ta.ittpizen.data.remote.response.job.CreateJobResult
import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.model.auth.LoginResult
import com.ta.ittpizen.domain.model.auth.RegisterResult
import com.ta.ittpizen.domain.model.connection.DetailConnection
import com.ta.ittpizen.domain.model.job.DetailJobResult
import com.ta.ittpizen.domain.model.post.CreatePostResult
import com.ta.ittpizen.domain.model.post.Post
import com.ta.ittpizen.domain.model.post.PostComment
import com.ta.ittpizen.domain.model.profile.Profile
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
        status: String,
        password: String
    ): Flow<Resource<RegisterResult>> {
        return repository.register(name, studentOrLectureId, email, phone, gender, status, password)
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
    ): Flow<Resource<Boolean>> {
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

    override fun createConnection(token: String, userId: String): Flow<Resource<Boolean>> {
        return repository.createConnection(token, userId)
    }

    override fun deleteConnection(token: String, userId: String): Flow<Resource<Boolean>> {
        return repository.deleteConnection(token, userId)
    }

    override fun createJob(
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
    ): Flow<Resource<CreateJobResult>> {
        return repository.createJob(
            token, title, company, street, city, province, workplaceType, jobType, description, skills, experience, graduates, link
        )
    }

    override fun getJobById(token: String, jobId: String): Flow<Resource<DetailJobResult>> {
        return repository.getJobById(token, jobId)
    }

    override fun saveJob(token: String, jobId: String): Flow<Resource<Boolean>> {
        return repository.saveJob(token, jobId)
    }

    override fun unSaveJob(token: String, jobId: String): Flow<Resource<Boolean>> {
        return repository.unSaveJob(token, jobId)
    }

    override fun getProfile(token: String): Flow<Resource<Profile>> {
        return repository.getProfile(token)
    }

    override fun updateProfile(
        token: String,
        name: String,
        bio: String,
        photo: File?
    ): Flow<Resource<Profile>> {
        return repository.updateProfile(token, name, bio, photo)
    }

}