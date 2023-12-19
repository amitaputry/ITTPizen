package com.ta.ittpizen.feature_onboarding_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.feature_onboarding_screen.R
import com.ta.ittpizen.feature_onboarding_screen.domain.Onboarding
import com.ta.ittpizen.ui.component.text.TextBodyLarge
import com.ta.ittpizen.ui.component.text.TextHeadlineSmall
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.PrimaryRed
import com.ta.ittpizen.ui.theme.SecondDarkGrey

@Composable
fun OnboardingContent(
    modifier: Modifier = Modifier,
    onboarding: Onboarding
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = onboarding.image),
            contentDescription = onboarding.title,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(onboarding.imageHeight)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.weight(1f))
        TextHeadlineSmall(
            text = onboarding.title,
            color = PrimaryRed,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 34.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextBodyLarge(
            text = onboarding.description,
            color = SecondDarkGrey,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 34.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
fun PreviewOnboardingContent() {
    ITTPizenTheme {
        Surface {
            val onboarding = Onboarding(
                image = R.drawable.img_onboarding_1,
                imageHeight = 286.dp,
                title = "Hi ITTPizen, start now!",
                description = "Let’s connect, sharing information and job with students, alumni, lecturer and other civitas at ITTPizen now."
            )
            OnboardingContent(
                onboarding = onboarding,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}