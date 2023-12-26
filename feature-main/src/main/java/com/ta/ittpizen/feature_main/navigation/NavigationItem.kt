package com.ta.ittpizen.feature_main.navigation

import androidx.annotation.DrawableRes

class NavigationItem (
    val title: String,
    val screen: Screen,
    @DrawableRes
    val icon: Int,
    @DrawableRes
    val iconActive: Int
)