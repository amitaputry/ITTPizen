package com.ta.ittpizen.navigation

import com.ta.ittpizen.feature_post.add.AddPostType

sealed class Screen(val route: String) {

    companion object {
        const val ADD_POST_TYPE = "add_post_type"
        const val POST_ID = "post_id"
        const val CHAT_ID = "chat_id"
        const val JOB_ID = "job_id"
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
}