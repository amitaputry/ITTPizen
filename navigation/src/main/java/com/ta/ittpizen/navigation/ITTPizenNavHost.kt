package com.ta.ittpizen.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ta.ittpizen.feature_auth.login.LoginScreen
import com.ta.ittpizen.feature_auth.register.RegisterScreen
import com.ta.ittpizen.feature_main.MainScreen
import com.ta.ittpizen.feature_onboarding_screen.OnboardingScreen
import com.ta.ittpizen.feature_splash_screen.SplashScreen

@ExperimentalMaterialApi
@ExperimentalLayoutApi
@ExperimentalMaterialNavigationApi
@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Composable
fun ITTPizenNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: Screen = Screen.MainScreen
) {
    val startDestinationRoute = startDestination.route
    NavHost(navController = navController, startDestination = startDestinationRoute) {
        composableWithSlideHorizontalAnimation(
            route = Screen.SplashScreen.route
        ) {
            SplashScreen(
                navigateToNextScreen = navController::navigateToOnboardingScreen
            )
        }
        composableWithSlideHorizontalAnimation(
            route = Screen.OnboardingScreen.route
        ) {
            OnboardingScreen(
                navigateToLoginScreen = navController::navigateToLoginScreen
            )
        }
        composableWithSlideHorizontalAnimation(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(
                navigateToRegisterScreen = navController::navigateToRegisterScreen
            )
        }
        composableWithSlideHorizontalAnimation(
            route = Screen.RegisterScreen.route
        ) {
            RegisterScreen(
                navigateUp = navController::navigateUp,
                navigateToLoginScreen = { navController.navigateToLoginScreen(from = Screen.LoginScreen) }
            )
        }
        composableWithSlideHorizontalAnimation(
            route = Screen.MainScreen.route
        ) {
            MainScreen()
        }
    }
}