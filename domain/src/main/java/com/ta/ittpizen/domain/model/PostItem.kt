package com.ta.ittpizen.domain.model

enum class PostItemType {
    TWEET,
    ACADEMIC,
    ACHIEVEMENT,
    EVENT,
    SCHOLARSHIP
}

data class PostItem(
    val id: String = "",
    val postType: PostItemType = PostItemType.TWEET,
    val profile: String = "",
    val type: String = "",
    val name: String = "",
    val date: String = "",
    val text: String = "",
    val media: String = "",
    val like: Int = 0,
    val comment: Int = 0,
    val liked: Boolean = false
)
