package com.ta.ittpizen.feature_notification.notification

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.domain.model.NotificationItem
import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.feature_notification.component.EmptyNotificationContent
import com.ta.ittpizen.feature_notification.component.NotificationItem
import com.ta.ittpizen.ui.component.topappbar.DetailTopAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalMaterial3Api
@Composable
fun NotificationScreen(
    modifier: Modifier = Modifier,
    userId: String = "",
    navigateUp: () -> Unit = {}
) {

    val dummyNotifications = (1..20).map {
        NotificationItem(
            id = it.toString(),
            name = "Abdul Hafiz Ramadan",
            message = "Liked your post"
        )
    }

    val notifications: Resource<List<NotificationItem>> = Resource.Success(emptyList())

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Notification",
                onNavigationClick = navigateUp
            )
        },
        modifier = modifier
    ) { paddingValues ->
        if (notifications is Resource.Success && notifications.data.isEmpty()) {
            EmptyNotificationContent(
                modifier = Modifier.padding(paddingValues)
            )
        }
        if (notifications is Resource.Success && notifications.data.isNotEmpty()) {
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(items = notifications.data, key = { it.id }) { notification ->
                    NotificationItem(
                        notification = notification,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewNotificationScreen() {
    ITTPizenTheme {
        Surface {
            NotificationScreen()
        }
    }
}
