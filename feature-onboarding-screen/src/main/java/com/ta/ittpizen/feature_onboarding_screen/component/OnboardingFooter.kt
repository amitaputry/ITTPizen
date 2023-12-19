package com.ta.ittpizen.feature_onboarding_screen.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.component.button.PrimaryButton

@ExperimentalFoundationApi
@Composable
fun OnboardingFooter(
    modifier: Modifier = Modifier,
    count: Int,
    activePage: Int,
    onFinishClick: () -> Unit = {},
    buttonFinishVisibility: Boolean = false
) {
    Box(modifier = modifier) {
        OnboardingPagerIndicator(
            count = count,
            activePage = activePage,
            modifier = Modifier.align(Alignment.Center)
        )
        Box(
            modifier = Modifier.height(40.dp)
                .align(Alignment.CenterEnd)
        ) {
            AnimatedVisibility(
                visible = buttonFinishVisibility,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                PrimaryButton(
                    text = "Let’s Go!",
                    onClick = onFinishClick,
                )
            }
        }
    }
}