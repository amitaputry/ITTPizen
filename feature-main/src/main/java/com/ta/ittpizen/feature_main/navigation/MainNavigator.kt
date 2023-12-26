package com.ta.ittpizen.feature_main.navigation

interface MainNavigator {

    // Bottom Navigation Screen
    fun navigateToHomeScreen()
    fun navigateToConnectionScreen()
    fun navigateToAddPostScreen()
    fun navigateToChatScreen()
    fun navigateToJobScreen()

    // Home Screen Navigator
    fun navigateToDetailPostScreen(id: String)
    fun navigateToProfileScreen()

    // Connection Screen Navigator
    fun navigateToDetailUserScreen(id: String)
    fun navigateToSearchConnectionScreen()

    // Chat Screen Navigator
    fun navigateToAddChatScreen()
    fun navigateToDetailChatScreen(id: String)

    // Job Screen Navigator
    fun navigateToAddJobScreen()
    fun navigateToDetailJobScreen(id: String)
    fun navigateToSearchJobScreen()
}

object EmptyMainNavigator : MainNavigator {
    override fun navigateToHomeScreen() {}
    override fun navigateToConnectionScreen() {}
    override fun navigateToAddPostScreen() {}
    override fun navigateToChatScreen() {}
    override fun navigateToJobScreen() {}
    override fun navigateToDetailPostScreen(id: String) {}
    override fun navigateToProfileScreen() {}
    override fun navigateToDetailUserScreen(id: String) {}
    override fun navigateToSearchConnectionScreen() {}
    override fun navigateToAddChatScreen() {}
    override fun navigateToDetailChatScreen(id: String) {}
    override fun navigateToAddJobScreen() {}
    override fun navigateToDetailJobScreen(id: String) {}
    override fun navigateToSearchJobScreen() {}
}
