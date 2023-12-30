package com.ta.ittpizen.feature_main.navigation

interface MainNavigator {
    // Add Post Navigator
    fun navigateToAddTweetScreen()
    fun navigateToAddAcademicScreen()
    fun navigateToAddAchievementScreen()
    fun navigateToAddEventScreen()
    fun navigateToAddScholarshipScreen()

    // Home Screen Navigator
    fun navigateToDetailPostScreen(postId: String)
    fun navigateToMyProfileScreen(userId: String)
    fun navigateToUserProfileScreen(userId: String)
    fun navigateToNotificationScreen(userId: String)

    // Connection Screen Navigator
    fun navigateToSearchConnectionScreen()

    // Chat Screen Navigator
    fun navigateToDetailChatScreen(chatId: String, friendId: String)

    // Job Screen Navigator
    fun navigateToAddJobScreen()
    fun navigateToDetailJobScreen(jobId: String)
    fun navigateToSearchJobScreen()

    fun navigateToPhotoDetailScreen(photo: String)

}

object EmptyMainNavigator : MainNavigator {
    override fun navigateToAddTweetScreen() {}
    override fun navigateToAddAcademicScreen() {}
    override fun navigateToAddAchievementScreen() {}
    override fun navigateToAddEventScreen() {}
    override fun navigateToAddScholarshipScreen() {}
    override fun navigateToDetailPostScreen(postId: String) {}
    override fun navigateToNotificationScreen(userId: String) {}
    override fun navigateToMyProfileScreen(userId: String) {}
    override fun navigateToUserProfileScreen(userId: String) {}
    override fun navigateToSearchConnectionScreen() {}
    override fun navigateToDetailChatScreen(chatId: String, friendId: String) {}
    override fun navigateToAddJobScreen() {}
    override fun navigateToDetailJobScreen(jobId: String) {}
    override fun navigateToSearchJobScreen() {}
    override fun navigateToPhotoDetailScreen(photo: String) {}
}
