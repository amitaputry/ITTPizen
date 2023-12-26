package com.ta.ittpizen.feature_main.navigation

sealed class Screen(val route: String) {
    object Home : Screen(route = "home")
    object Connection : Screen(route = "connection")
    object AddPost : Screen(route = "add-post")
    object Chat : Screen(route = "chat")
    object Job : Screen(route = "job")
}