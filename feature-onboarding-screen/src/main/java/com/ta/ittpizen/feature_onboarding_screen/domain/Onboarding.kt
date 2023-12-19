package com.ta.ittpizen.feature_onboarding_screen.domain

import androidx.annotation.DrawableRes

data class Onboarding(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
)
