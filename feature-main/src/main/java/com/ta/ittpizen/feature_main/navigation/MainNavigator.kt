package com.ta.ittpizen.feature_main.navigation

interface MainNavigator {
    // Add Post Navigator
    fun navigateToAddTweetScreen()
    fun navigateToAddAcademicScreen()
    fun navigateToAddAchievementScreen()
    fun navigateToAddEventScreen()
    fun navigateToAddScholarshipScreen()

    // Home Screen Navigator
    fun navigateToDetailPostScreen(id: String)
    fun navigateToProfileScreen()
    fun navigateToNotificationScreen()

    // Connection Screen Navigator
    fun navigateToDetailUserScreen(id: String)
    fun navigateToSearchConnectionScreen()

    // Chat Screen Navigator
    fun navigateToDetailChatScreen(id: String)

    // Job Screen Navigator
    fun navigateToAddJobScreen()
    fun navigateToDetailJobScreen(id: String)
    fun navigateToSearchJobScreen()
}

object EmptyMainNavigator : MainNavigator {
    override fun navigateToAddTweetScreen() {}
    override fun navigateToAddAcademicScreen() {}
    override fun navigateToAddAchievementScreen() {}
    override fun navigateToAddEventScreen() {}
    override fun navigateToAddScholarshipScreen() {}
    override fun navigateToDetailPostScreen(id: String) {}
    override fun navigateToNotificationScreen() {}

    override fun navigateToProfileScreen() {}
    override fun navigateToDetailUserScreen(id: String) {}
    override fun navigateToSearchConnectionScreen() {}
    override fun navigateToDetailChatScreen(id: String) {}
    override fun navigateToAddJobScreen() {}
    override fun navigateToDetailJobScreen(id: String) {}
    override fun navigateToSearchJobScreen() {}
}
