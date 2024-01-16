package com.ta.ittpizen.data.remote.response.post

import com.google.gson.annotations.SerializedName

data class PostUserResponse(

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("photo")
    val photo: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("type")
    val type: String? = null
)
