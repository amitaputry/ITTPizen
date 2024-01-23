package com.ta.ittpizen.domain.model.post

import androidx.compose.runtime.Immutable

@Immutable
data class PostUser(

    val id: String = "",

    val photo: String = "",

    val name: String = "",

    val email: String = "",

    val type: String = ""
)
