package com.ta.ittpizen.feature_post.success

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ta.ittpizen.feature_post.R
import com.ta.ittpizen.ui.component.button.LargePrimaryButton
import com.ta.ittpizen.ui.component.text.TextBodyMedium
import com.ta.ittpizen.ui.component.textbutton.TextButton
import com.ta.ittpizen.ui.theme.ColorText
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.PrimaryRed

@Composable
fun SuccessAddPostScreen(
    modifier: Modifier = Modifier,
    navigateToPostScreen: () -> Unit = {},
    navigateToMainScreen: () -> Unit = {},
) {
    Scaffold(
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_success_add_post),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(40.dp))
            TextBodyMedium(
                text = "Yeay, your post has been uploaded!",
                textAlign = TextAlign.Center,
                color = PrimaryRed,
                fontWeight = FontWeight.Medium
            )
            TextBodyMedium(
                text = "Thank you for sharing your content with us :)",
                color = ColorText,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(40.dp))
            LargePrimaryButton(
                text = "See Post",
                onClick = navigateToPostScreen,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextButton(
                text = "Finish",
                onClick = navigateToMainScreen,
                fontSize = 14.sp,
                color = ColorText,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview
@Composable
fun PreviewSuccessAddPostScreen() {
    ITTPizenTheme {
        Surface {
            SuccessAddPostScreen()
        }
    }
}
