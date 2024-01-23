package com.ta.ittpizen.feature_profile.edit

data class EditProfileUiState(
    val photo: String = "",
    val name: String = "",
    val type: String = "",
    val studentOrLectureId: String = "",
    val displayName: String = "",
    val bio: String = "",
    val displayNameErrorName: String = "",
)
