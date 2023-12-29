package com.ta.ittpizen.feature_notification.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.feature_notification.R
import com.ta.ittpizen.ui.component.text.TextBodyLarge
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun EmptyNotificationContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.img_no_notification), contentDescription = null)
        Spacer(modifier = Modifier.height(20.dp))
        TextBodyLarge(text = "No Notification :(", textAlign = TextAlign.Center)
    }
}

@Preview
@Composable
fun PreviewEmptyNotificationContent() {
    ITTPizenTheme {
        Surface {
            EmptyNotificationContent()
        }
    }
}
