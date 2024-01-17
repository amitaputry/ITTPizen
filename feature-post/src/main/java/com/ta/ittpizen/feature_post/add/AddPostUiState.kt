package com.ta.ittpizen.feature_post.add

import android.net.Uri

data class AddPostUiState(
    val media: Uri? = null,
    val text: String = "",
    val type: String = ""
)
