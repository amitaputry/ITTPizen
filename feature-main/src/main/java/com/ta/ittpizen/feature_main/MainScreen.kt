package com.ta.ittpizen.feature_main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ta.ittpizen.feature_chat.chat.ChatScreen
import com.ta.ittpizen.feature_connection.connection.ConnectionScreen
import com.ta.ittpizen.feature_home.HomeScreen
import com.ta.ittpizen.feature_job.job.JobScreen
import com.ta.ittpizen.feature_main.component.BaseBottomNavigation
import com.ta.ittpizen.feature_main.navigation.EmptyMainNavigator
import com.ta.ittpizen.feature_main.navigation.MainNavigator
import com.ta.ittpizen.feature_main.navigation.Screen
import com.ta.ittpizen.ui.component.bottomsheet.AddPostBottomSheet
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@ExperimentalLayoutApi
@ExperimentalFoundationApi
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainNavigator: MainNavigator = EmptyMainNavigator,
    navController: NavHostController = rememberNavController()
) {

    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    val toggleBottomSheet: () -> Unit = {
        scope.launch {
            if (sheetState.isVisible) {
                sheetState.hide()
                return@launch
            }
            sheetState.show()
        }
    }

    val navigateToAddTweetScreen: () -> Unit = {
        toggleBottomSheet()
        mainNavigator.navigateToAddTweetScreen()
    }

    val navigateToAddAcademicScreen: () -> Unit = {
        toggleBottomSheet()
        mainNavigator.navigateToAddAcademicScreen()
    }

    val navigateToAddAchievementScreen: () -> Unit = {
        toggleBottomSheet()
        mainNavigator.navigateToAddAchievementScreen()
    }

    val navigateToAddEventScreen: () -> Unit = {
        toggleBottomSheet()
        mainNavigator.navigateToAddEventScreen()
    }

    val navigateToAddScholarshipScreen: () -> Unit = {
        toggleBottomSheet()
        mainNavigator.navigateToAddScholarshipScreen()
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetElevation = 0.dp,
        sheetBackgroundColor = Color.Transparent,

        sheetContent = {
            AddPostBottomSheet(
                closeBottomSheet = toggleBottomSheet,
                navigateToAddTweetScreen = navigateToAddTweetScreen,
                navigateToAddAcademicScreen = navigateToAddAcademicScreen,
                navigateToAddAchievementScreen = navigateToAddAchievementScreen,
                navigateToAddEventScreen = navigateToAddEventScreen,
                navigateToAddScholarshipScreen = navigateToAddScholarshipScreen
            )
        }
    ) {
        Scaffold(
            bottomBar = {
                BaseBottomNavigation(
                    navController = navController,
                    onAddPostClick = toggleBottomSheet
                )
            },
            modifier = modifier
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(route = Screen.Home.route) {
                    HomeScreen(
                        navigateToMyProfileScreen = mainNavigator::navigateToMyProfileScreen,
                        navigateToUserProfileScreen = mainNavigator::navigateToUserProfileScreen,
                        navigateToNotificationScreen = mainNavigator::navigateToNotificationScreen,
                        navigateToDetailPostScreen = mainNavigator::navigateToDetailPostScreen,
                        navigateToPhotoDetailScreen = mainNavigator::navigateToPhotoDetailScreen
                    )
                }
                composable(route = Screen.Connection.route) {
                    ConnectionScreen(
                        navigateToSearchConnectionScreen = mainNavigator::navigateToSearchConnectionScreen,
                        navigateToDetailUserScreen = mainNavigator::navigateToUserProfileScreen
                    )
                }
                composable(route = Screen.Chat.route) {
                    ChatScreen(
                        navigateToDetailChatScreen = mainNavigator::navigateToDetailChatScreen
                    )
                }
                composable(route = Screen.Job.route) {
                    JobScreen(
                        navigateToAddJobScreen = mainNavigator::navigateToAddJobScreen,
                        navigateToDetailJobScreen = mainNavigator::navigateToDetailJobScreen,
                        navigateToSearchJobScreen = mainNavigator::navigateToSearchJobScreen
                    )
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewMainScreen() {
    ITTPizenTheme {
        Surface {
            MainScreen()
        }
    }
}
