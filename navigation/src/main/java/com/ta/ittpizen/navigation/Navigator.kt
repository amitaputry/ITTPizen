package com.ta.ittpizen.navigation

import androidx.navigation.NavHostController
import kotlin.jvm.Throws

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToOnboardingScreen(
    from: Screen = Screen.SplashScreen,
    clearBackStack: Boolean = true
) {
    navigate(route = Screen.OnboardingScreen.route) {
        if (!clearBackStack) return@navigate
        popUpTo(from.route) {
            inclusive = true
        }
    }
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToLoginScreen(
    from: Screen = Screen.OnboardingScreen,
    clearBackStack: Boolean = true
) {
    navigate(route = Screen.LoginScreen.route) {
        if (!clearBackStack) return@navigate
        popUpTo(from.route) {
            inclusive = true
        }
    }
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToRegisterScreen() {
    navigate(Screen.RegisterScreen.route)
}
