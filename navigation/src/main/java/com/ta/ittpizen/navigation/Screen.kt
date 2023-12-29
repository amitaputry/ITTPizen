package com.ta.ittpizen.navigation

import com.ta.ittpizen.feature_post.add.AddPostType
import com.ta.ittpizen.feature_profile.profile.ProfileScreenType

sealed class Screen(val route: String) {

    companion object {
        const val ADD_POST_TYPE = "add_post_type"
        const val POST_ID = "post_id"
        const val CHAT_ID = "chat_id"
        const val JOB_ID = "job_id"
        const val PROFILE_TYPE = "profile_type"
        const val USER_ID = "user_id"
    }

    object SplashScreen : Screen(route = "splash-screen")
    object OnboardingScreen : Screen(route = "onboarding-screen")
    object LoginScreen : Screen(route = "login-screen")
    object RegisterScreen : Screen(route = "register-screen")
    object MainScreen : Screen(route = "main-screen")
    object SearchConnectionScreen : Screen(route = "search-connection-screen")
    object SearchJobScreen : Screen(route = "search-job-screen")
    object AddJobScreen : Screen(route = "add-job-screen")
    object SuccessAddPostScreen : Screen(route = "success-add-post-screen/{$POST_ID}") {
        fun generateRoute(postId: String): String {
            return "success-add-post-screen/$postId"
        }
    }
    object AddPostScreen : Screen(route = "add-post-screen/{$ADD_POST_TYPE}") {
        fun generateRoute(type: AddPostType): String {
            return "add-post-screen/${type.name}"
        }
    }
    object PostDetailScreen : Screen(route = "post-detail-screen/{$POST_ID}") {
        fun generateRoute(postId: String): String {
            return "post-detail-screen/$postId"
        }
    }
    object DetailChatScreen : Screen(route = "detail-chat-screen/{$CHAT_ID}") {
        fun generateRoute(chatId: String): String {
            return "detail-chat-screen/$chatId"
        }
    }
    object JobDetailScreen : Screen(route = "detail-job-screen/{$JOB_ID}") {
        fun generateRoute(jobId: String): String {
            return "detail-job-screen/$jobId"
        }
    }
    object ProfileScreen : Screen(route = "profile-screen/{$PROFILE_TYPE}/{$USER_ID}") {
        fun generateRoute(type: ProfileScreenType, userId: String): String {
            return "profile-screen/${type.name}/$userId"
        }
    }
    object EditProfileScreen : Screen(route = "edit-profile-screen/{$USER_ID}") {
        fun generateRoute(userId: String): String {
            return "edit-profile-screen/$userId"
        }
    }
    object NotificationScreen : Screen(route = "notification-screen/{$USER_ID}") {
        fun generateRoute(userId: String): String {
            return "notification-screen/$userId"
        }
    }
    object SavedJobScreen : Screen(route = "saved-job-screen/{$USER_ID}") {
        fun generateRoute(userId: String): String {
            return "saved-job-screen/$userId"
        }
    }
}