package com.ta.ittpizen.feature_job.add

data class AddJobGeneralUiState(
    val jobTitle: String = "",
    val companyName: String = "",
    val street: String = "",
    val city: String = "",
    val province: String = "",
    val workplaceType: String = "",
    val jobType: String = "",

    val jobTitleErrorMessage: String = "",
    val companyNameErrorMessage: String = "",
    val streetErrorMessage: String = "",
    val cityErrorMessage: String = "",
    val provinceErrorMessage: String = "",
)
