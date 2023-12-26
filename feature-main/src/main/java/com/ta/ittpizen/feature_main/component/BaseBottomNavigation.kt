package com.ta.ittpizen.feature_main.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ta.ittpizen.feature_main.R
import com.ta.ittpizen.feature_main.navigation.NavigationItem
import com.ta.ittpizen.feature_main.navigation.Screen
import com.ta.ittpizen.ui.component.iconbutton.BaseIconButton
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun BaseBottomNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onAddPostClick: () -> Unit = {}
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val home = NavigationItem(
        title = "Home",
        screen = Screen.Home,
        icon = R.drawable.ic_home,
        iconActive = R.drawable.ic_home_active
    )
    val connection = NavigationItem(
        title = "Connection",
        screen = Screen.Connection,
        icon = R.drawable.ic_connection,
        iconActive = R.drawable.ic_connection_active
    )
    val addPost = NavigationItem(
        title = "Add Post",
        screen = Screen.AddPost,
        icon = R.drawable.ic_add_post,
        iconActive = R.drawable.ic_add_post_active
    )
    val chat = NavigationItem(
        title = "Chat",
        screen = Screen.Chat,
        icon = R.drawable.ic_chat,
        iconActive = R.drawable.ic_chat_active
    )
    val job = NavigationItem(
        title = "Job",
        screen = Screen.Job,
        icon = R.drawable.ic_job,
        iconActive = R.drawable.ic_job_active
    )

    Row(
        modifier = modifier
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            )
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .height(70.dp)
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val homeActive = currentDestination?.hierarchy?.any { it.route == home.screen.route } == true
        val homeIcon = if (homeActive) home.iconActive else home.icon
        BaseIconButton(
            icon = painterResource(id = homeIcon),
            contentDescription = home.title,
            size = 28.dp,
            onClick = {
                navController.navigate(home.screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        val connectionActive = currentDestination?.hierarchy?.any { it.route == connection.screen.route } == true
        val connectionIcon = if (connectionActive) connection.iconActive else connection.icon
        BaseIconButton(
            icon = painterResource(id = connectionIcon),
            contentDescription = connection.title,
            size = 28.dp,
            onClick = {
                navController.navigate(connection.screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        val addPostActive = currentDestination?.hierarchy?.any { it.route == addPost.screen.route } == true
        val addPostIcon = if (addPostActive) addPost.iconActive else addPost.icon
        BaseIconButton(
            icon = painterResource(id = addPostIcon),
            contentDescription = addPost.title,
            size = 28.dp,
            onClick = onAddPostClick
        )

        val chatActive = currentDestination?.hierarchy?.any { it.route == chat.screen.route } == true
        val chatIcon = if (chatActive) chat.iconActive else chat.icon
        BaseIconButton(
            icon = painterResource(id = chatIcon),
            contentDescription = chat.title,
            size = 28.dp,
            onClick = {
                navController.navigate(chat.screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        val jobActive = currentDestination?.hierarchy?.any { it.route == job.screen.route } == true
        val jobIcon = if (jobActive) job.iconActive else job.icon
        BaseIconButton(
            icon = painterResource(id = jobIcon),
            contentDescription = job.title,
            size = 28.dp,
            onClick = {
                navController.navigate(job.screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun PreviewBaseBottomNavigation() {
    ITTPizenTheme {
        Surface {
            Scaffold(
                bottomBar = {
                    BaseBottomNavigation()
                }
            ) {

            }
        }
    }
}
