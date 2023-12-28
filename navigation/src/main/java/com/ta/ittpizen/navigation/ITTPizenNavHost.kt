package com.ta.ittpizen.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ta.ittpizen.feature_auth.login.LoginScreen
import com.ta.ittpizen.feature_auth.register.RegisterScreen
import com.ta.ittpizen.feature_chat.detail.DetailChatScreen
import com.ta.ittpizen.feature_connection.search.SearchConnectionScreen
import com.ta.ittpizen.feature_job.add.AddJobScreen
import com.ta.ittpizen.feature_job.detail.JobDetailScreen
import com.ta.ittpizen.feature_job.search.SearchJobScreen
import com.ta.ittpizen.feature_main.MainScreen
import com.ta.ittpizen.feature_onboarding_screen.OnboardingScreen
import com.ta.ittpizen.feature_post.add.AddPostScreen
import com.ta.ittpizen.feature_post.add.AddPostType
import com.ta.ittpizen.feature_post.detail.PostDetailScreen
import com.ta.ittpizen.feature_post.success.SuccessAddPostScreen
import com.ta.ittpizen.feature_profile.profile.ProfileScreen
import com.ta.ittpizen.feature_profile.profile.ProfileScreenType
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
                navigateToRegisterScreen = navController::navigateToRegisterScreen,
                navigateToMainScreen = { navController.navigateToMainScreen(from = Screen.LoginScreen) }
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
            val mainNavigator = MainNavigator(navController)
            MainScreen(mainNavigator = mainNavigator)
        }
        composableWithSlideHorizontalAnimation(
            route = Screen.SearchConnectionScreen.route
        ) {
            SearchConnectionScreen(
                navigateUp = navController::navigateUp,
                navigateToDetailConnection = {}
            )
        }
        composableWithSlideHorizontalAnimation(
            route = Screen.SearchJobScreen.route
        ) {
            SearchJobScreen(
                navigateUp = navController::navigateUp,
                navigateToDetailJob = { navController.navigateToJobDetailScreen(jobId = it) }
            )
        }
        composableWithSlideHorizontalAnimation(
            route = Screen.AddJobScreen.route
        ) {
            AddJobScreen(
                navigateUp = navController::navigateUp,
                navigateToDetailJob = { navController.navigateToJobDetailScreen(jobId = it) }
            )
        }
        composableWithSlideHorizontalAnimation(
            route = Screen.SuccessAddPostScreen.route,
            arguments = listOf(
                navArgument(Screen.POST_ID) {
                    type = NavType.StringType
                }
            )
        ) {
            val postId = it.arguments?.getString(Screen.POST_ID) ?: ""
            SuccessAddPostScreen(
                navigateToDetailPostScreen = { navController.navigateToPostDetailScreen(postId = postId) },
                navigateToMainScreen = { navController.navigateToMainScreen(from = Screen.MainScreen) }
            )
        }
        composableWithSlideHorizontalAnimation(
            route = Screen.AddPostScreen.route,
            arguments = listOf(
                navArgument(Screen.ADD_POST_TYPE) {
                    type = NavType.StringType
                }
            )
        ) {
            val type = it.arguments?.getString(Screen.ADD_POST_TYPE) ?: AddPostType.TWEET.name
            val addPostType = AddPostType.valueOf(type)
            AddPostScreen(
                navigateUp = navController::navigateUp,
                navigateToSuccessAddPostScreen = { navController.navigateToSuccessAddPostScreen(postId = it) },
                type = addPostType,
            )
        }
        composableWithSlideHorizontalAnimation(
            route = Screen.DetailChatScreen.route,
            arguments = listOf(
                navArgument(Screen.CHAT_ID) {
                    type = NavType.StringType
                }
            )
        ) {
            val chatId = it.arguments?.getString(Screen.CHAT_ID) ?: ""
            DetailChatScreen(
                navigateUp = navController::navigateUp,
                id = chatId,
            )
        }
        composableWithSlideHorizontalAnimation(
            route = Screen.JobDetailScreen.route,
            arguments = listOf(
                navArgument(Screen.JOB_ID) {
                    type = NavType.StringType
                }
            )
        ) {
            val jobId = it.arguments?.getString(Screen.JOB_ID) ?: ""
            JobDetailScreen(
                navigateUp = navController::navigateUp,
                jobId = jobId
            )
        }
        composableWithSlideHorizontalAnimation(
            route = Screen.PostDetailScreen.route,
            arguments = listOf(
                navArgument(Screen.POST_ID) {
                    type = NavType.StringType
                }
            )
        ) {
            val postId = it.arguments?.getString(Screen.POST_ID) ?: ""
            PostDetailScreen(
                navigateUp = navController::navigateUp,
                postId = postId
            )
        }
        composableWithSlideHorizontalAnimation(
            route = Screen.ProfileScreen.route,
            arguments = listOf(
                navArgument(Screen.PROFILE_TYPE) {
                    type = NavType.StringType
                },
                navArgument(Screen.USER_ID) {
                    type = NavType.StringType
                },
            )
        ) {
            val type = it.arguments?.getString(Screen.PROFILE_TYPE) ?: ProfileScreenType.ME.name
            val userId = it.arguments?.getString(Screen.USER_ID) ?: ""
            val userType = ProfileScreenType.valueOf(type)
            ProfileScreen(
                navigateUp = navController::navigateUp,
                type = userType,
                userId = userId,
                navigateToEditProfile = {},
                navigateToDetailPostScreen = { navController.navigateToPostDetailScreen(postId = it) },
                navigateToSavedJob = {}
            )
        }
    }
}