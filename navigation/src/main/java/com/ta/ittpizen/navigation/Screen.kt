package com.ta.ittpizen.navigation

sealed class Screen(val route: String) {
    object SplashScreen : Screen(route = "splash-screen")
    object OnboardingScreen : Screen(route = "onboarding-screen")
    object LoginScreen : Screen(route = "login-screen")
    object RegisterScreen : Screen(route = "register-screen")
    object MainScreen : Screen(route = "main-screen")
}