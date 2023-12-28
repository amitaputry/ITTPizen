package com.ta.ittpizen.feature_job.add

data class AddJobDetailUiState(
    val description: String = "",
    val skills: List<String> = listOf(""),
    val experience: String = "",
    val graduate: String = "",
    val linkApplication: String = "",

    val descriptionErrorMessage: String = ""
)
