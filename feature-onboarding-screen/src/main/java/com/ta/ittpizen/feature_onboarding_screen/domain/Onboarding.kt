package com.ta.ittpizen.feature_onboarding_screen.domain

import androidx.annotation.DrawableRes
import androidx.compose.ui.unit.Dp

data class Onboarding(
    @DrawableRes
    val image: Int,
    val imageHeight: Dp,
    val title: String,
    val description: String
)
