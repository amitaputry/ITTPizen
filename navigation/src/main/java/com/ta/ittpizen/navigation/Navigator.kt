package com.ta.ittpizen.navigation

import androidx.navigation.NavHostController
import com.ta.ittpizen.feature_main.navigation.MainNavigator
import com.ta.ittpizen.feature_post.add.AddPostType
import com.ta.ittpizen.feature_profile.profile.ProfileScreenType
import kotlin.jvm.Throws

class MainNavigator(
    private val navController: NavHostController
) : MainNavigator {
    override fun navigateToAddTweetScreen() = navController.navigateToAddPostScreen(AddPostType.TWEET)
    override fun navigateToAddAcademicScreen() = navController.navigateToAddPostScreen(AddPostType.ACADEMIC)
    override fun navigateToAddAchievementScreen() = navController.navigateToAddPostScreen(AddPostType.ACHIEVEMENT)
    override fun navigateToAddEventScreen() = navController.navigateToAddPostScreen(AddPostType.EVENT)
    override fun navigateToAddScholarshipScreen() = navController.navigateToAddPostScreen(AddPostType.SCHOLARSHIP)
    override fun navigateToDetailPostScreen(id: String) = navController.navigateToPostDetailScreen(postId = id)
    override fun navigateToMyProfileScreen(userId: String) = navController.navigateToProfileScreen(type = ProfileScreenType.ME, userId = userId)
    override fun navigateToUserProfileScreen(userId: String) = navController.navigateToProfileScreen(type = ProfileScreenType.FRIEND, userId = userId)
    override fun navigateToNotificationScreen() {}
    override fun navigateToSearchConnectionScreen() = navController.navigateToSearchConnectionScreen()
    override fun navigateToDetailChatScreen(id: String) = navController.navigateToDetailChatScreen(id)
    override fun navigateToAddJobScreen() = navController.navigateToAddJobScreen()
    override fun navigateToDetailJobScreen(id: String) = navController.navigateToJobDetailScreen(jobId = id)
    override fun navigateToSearchJobScreen() = navController.navigateToSearchJobScreen()
}

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
fun NavHostController.navigateToMainScreen(
    from: Screen = Screen.SplashScreen,
    clearBackStack: Boolean = true
) {
    navigate(route = Screen.MainScreen.route) {
        if (!clearBackStack) return@navigate
        popUpTo(from.route) {
            inclusive = true
        }
    }
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToSuccessAddPostScreen(
    from: Screen = Screen.AddPostScreen,
    clearBackStack: Boolean = true,
    postId: String
) {
    navigate(route = Screen.SuccessAddPostScreen.generateRoute(postId)) {
        if (!clearBackStack) return@navigate
        popUpTo(from.route) {
            inclusive = true
        }
    }
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToJobDetailScreen(
    from: Screen = Screen.AddJobScreen,
    clearBackStack: Boolean = true,
    jobId: String
) {
    navigate(Screen.JobDetailScreen.generateRoute(jobId)) {
        if (!clearBackStack) return@navigate
        popUpTo(from.route) {
            inclusive = true
        }
    }
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToPostDetailScreen(
    from: Screen = Screen.SuccessAddPostScreen,
    clearBackStack: Boolean = true,
    postId: String,
) {
    navigate(Screen.PostDetailScreen.generateRoute(postId)) {
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

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToSearchConnectionScreen() {
    navigate(Screen.SearchConnectionScreen.route)
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToSearchJobScreen() {
    navigate(Screen.SearchJobScreen.route)
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToAddJobScreen() {
    navigate(Screen.AddJobScreen.route)
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToAddPostScreen(type: AddPostType) {
    navigate(Screen.AddPostScreen.generateRoute(type))
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToDetailChatScreen(chatId: String) {
    navigate(Screen.DetailChatScreen.generateRoute(chatId))
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToProfileScreen(type: ProfileScreenType, userId: String) {
    navigate(Screen.ProfileScreen.generateRoute(type, userId))
}

@Throws(IllegalArgumentException::class)
fun NavHostController.navigateToEditProfileScreen(userId: String) {
    navigate(Screen.EditProfileScreen.generateRoute(userId))
}
