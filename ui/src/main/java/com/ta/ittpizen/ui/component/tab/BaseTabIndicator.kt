package com.ta.ittpizen.ui.component.tab

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TabPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.ta.ittpizen.ui.theme.PrimaryRed

@ExperimentalFoundationApi
@Composable
fun BaseTabIndicator(
    modifier: Modifier = Modifier,
    tabPositions: List<TabPosition>,
    selectedTabIndex: Int = 0
) {
    val transition = updateTransition(selectedTabIndex, label = "custom-tab-indicator")
    val indicatorStart by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 500f)
            } else {
                spring(dampingRatio = 1f, stiffness = 1000f)
            }
        },
        label = "indicator-start"
    ) {
        val additionOffset = (-15).dp
        tabPositions[it].left + additionOffset
    }
    val indicatorEnd by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 1000f)
            } else {
                spring(dampingRatio = 1f, stiffness = 500f)
            }
        },
        label = "indicator-end"
    ) {
        val additionOffset = (-15).dp
        tabPositions[it].right + additionOffset
    }

    Box(
        modifier = modifier
            .offset(x = indicatorStart)
            .wrapContentSize(align = Alignment.BottomStart)
            .width(indicatorEnd - indicatorStart)
            .fillMaxSize()
            .background(color = PrimaryRed, RoundedCornerShape(100))
            .border(BorderStroke(1.dp, PrimaryRed), RoundedCornerShape(100))
            .zIndex(1f)
    )
}
