package com.ta.ittpizen.ui.component.tab

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.ta.ittpizen.ui.component.text.TextBodyMedium
import com.ta.ittpizen.ui.theme.DisableColorGrey
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalLayoutApi
@ExperimentalFoundationApi
@Composable
fun BaseScrollableTabRow(
    modifier: Modifier = Modifier,
    selectedTabIndex: Int = 0,
    tabs: List<String> = emptyList(),
    onSelected: (Int) -> Unit = {},
    edgePadding: Dp = 0.dp
) {
    val indicator = @Composable { tabPositions: List<TabPosition> ->
        BaseTabIndicator(tabPositions = tabPositions, selectedTabIndex = selectedTabIndex)
    }
    Box {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            divider = {},
            indicator = indicator,
            edgePadding = edgePadding,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .then(modifier)
        ) {
            tabs.forEachIndexed { index, tab ->
                val selected = index == selectedTabIndex
                val textColor by animateColorAsState(
                    targetValue = if (selected) Color.White else DisableColorGrey,
                    label = ""
                )
                val xOffset = (-15).dp
                Tab(
                    selected = selected,
                    onClick = { onSelected(index) },
                    text = {
                        TextBodyMedium(text = tab, color = textColor, fontWeight = FontWeight.Medium)
                    },
                    modifier = Modifier
                        .offset(x = xOffset)
                        .zIndex(2f)
                        .height(34.dp)
                        .clip(RoundedCornerShape(100.dp))
                )
            }
        }
    }
}

@ExperimentalLayoutApi
@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewBaseScrollableTabRow() {
    ITTPizenTheme {
        Surface {
            val tabs = listOf("All Post", "Tweet", "Academic", "#PrestasiITTP", "Events", "Scholarship")
            var selectedTabIndex by remember { mutableIntStateOf(0) }
            BaseScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                tabs = tabs,
                onSelected = { selectedTabIndex = it },
                edgePadding = 20.dp,
                modifier = Modifier.padding(vertical = 20.dp)
            )
        }
    }
}
