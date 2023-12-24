package com.ta.ittpizen.feature_onboarding_screen.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.component.textbutton.TextButton

@Composable
fun OnboardingHeader(
    modifier: Modifier = Modifier,
    onSkipClick: () -> Unit = {},
    buttonSkipVisibility: Boolean = true
) {
    Box(
        modifier = Modifier
            .height(39.dp)
            .padding(horizontal = 20.dp)
            .then(modifier),
        contentAlignment = Alignment.Center,
    ) {
        AnimatedVisibility(
            visible = buttonSkipVisibility,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            TextButton(
                text = "Skip",
                onClick = onSkipClick,
            )
        }
    }

}