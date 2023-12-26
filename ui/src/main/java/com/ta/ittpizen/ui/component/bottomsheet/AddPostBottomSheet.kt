package com.ta.ittpizen.ui.component.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.component.iconbutton.FilledIconButton
import com.ta.ittpizen.ui.component.image.ImageWithCaption
import com.ta.ittpizen.ui.component.text.TextBodyMedium
import com.ta.ittpizen.ui.theme.ColorText
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.SecondDarkGrey

@ExperimentalLayoutApi
@Composable
fun ColumnScope.AddPostBottomSheet(
    closeBottomSheet: () -> Unit = {},
    navigateToAddTweetScreen: () -> Unit = {},
    navigateToAddAcademicScreen: () -> Unit = {},
    navigateToAddAchievementScreen: () -> Unit = {},
    navigateToAddEventScreen: () -> Unit = {},
    navigateToAddScholarshipScreen: () -> Unit = {},
) {
    FilledIconButton(
        icon = painterResource(id = R.drawable.ic_close_primary),
        contentDescription = "Close bottom sheet",
        size = 44.dp,
        onClick = closeBottomSheet,
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .align(Alignment.End)
            .padding(end = 20.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextBodyMedium(
            text = "#SosmednyaWargaITTPizen",
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = ColorText,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextBodyMedium(
            text = "Share your interesting stories with ITTPizen and express yourself.",
            textAlign = TextAlign.Center,
            color = SecondDarkGrey,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        FlowRow(
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.width(30.dp))
            ImageWithCaption(
                painter = painterResource(id = R.drawable.ic_add_tweet),
                caption = "Tweet",
                onClick = navigateToAddTweetScreen
            )
            Spacer(modifier = Modifier.width(30.dp))
            ImageWithCaption(
                painter = painterResource(id = R.drawable.ic_add_academic),
                caption = "Academic",
                onClick = navigateToAddAcademicScreen
            )
            Spacer(modifier = Modifier.width(30.dp))
            ImageWithCaption(
                painter = painterResource(id = R.drawable.ic_add_achievement),
                caption = "Achievement",
                onClick = navigateToAddAchievementScreen
            )
            Spacer(modifier = Modifier.width(30.dp))
            ImageWithCaption(
                painter = painterResource(id = R.drawable.ic_add_events),
                caption = "Event",
                onClick = navigateToAddEventScreen
            )
            Spacer(modifier = Modifier.width(30.dp))
            ImageWithCaption(
                painter = painterResource(id = R.drawable.ic_add_scholarship),
                caption = "Scholarship",
                onClick = navigateToAddScholarshipScreen
            )
        }
    }
}

@ExperimentalLayoutApi
@Preview
@Composable
fun PreviewAddPostBottomSheet() {
    ITTPizenTheme {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)),
                verticalArrangement = Arrangement.Bottom
            ) {
                AddPostBottomSheet()
            }
        }
    }
}
