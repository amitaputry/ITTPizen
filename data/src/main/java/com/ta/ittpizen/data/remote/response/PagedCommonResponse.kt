package com.ta.ittpizen.data.remote.response

import com.google.gson.annotations.SerializedName

data class PagedCommonResponse<out T>(

    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("data")
    val data: T,

    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("nextPage")
    val nextPage: Int? = null,

    @field:SerializedName("prevPage")
    val prevPage: Int? = null,

    @field:SerializedName("message")
    val message: String? = null
)