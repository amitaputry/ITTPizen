package com.ta.ittpizen.feature_post.detail

import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.model.post.Post
import com.ta.ittpizen.domain.model.post.PostComment

data class PostDetailUiState(
    val post: Resource<Post> = Resource.Loading,
    val postComment: Resource<List<PostComment>> = Resource.Loading
)
