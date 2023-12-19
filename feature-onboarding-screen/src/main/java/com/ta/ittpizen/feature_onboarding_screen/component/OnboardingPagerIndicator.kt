package com.ta.ittpizen.feature_onboarding_screen.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.theme.HoverRed
import com.ta.ittpizen.ui.theme.PrimaryRed

@ExperimentalFoundationApi
@Composable
fun OnboardingPagerIndicator(
    modifier: Modifier = Modifier,
    count: Int,
    activePage: Int
) {
    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(count) { iteration ->
            val isActive = activePage == iteration
            val color = if (isActive) PrimaryRed else HoverRed
            val width = if (isActive) 20.dp else 8.dp
            val animatedColor by animateColorAsState(targetValue = color, label = "color-animation")
            val animatedWidth by animateDpAsState(targetValue = width, label = "width-animation")
            Box(
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .clip(CircleShape)
                    .height(8.dp)
                    .width(animatedWidth)
                    .background(animatedColor)
            )
        }
    }
}