package com.ta.ittpizen.ui.component.history

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.component.button.LargeButtonTertiary
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalFoundationApi
@Composable
fun HistoryContent(
    modifier: Modifier = Modifier,
    histories: List<String> = emptyList(),
    onDeleteAllClick: () -> Unit = {},
    onHistoryClick: (Int) -> Unit = {},
    onDeleteHistoryClick: (Int) -> Unit = {},
    onSeeMoreClick: () -> Unit = {}
) {
    LazyColumn(modifier = modifier) {
        stickyHeader {
            HistoryHeader(
                onDeleteAllClick = onDeleteAllClick,
                modifier = Modifier.padding(horizontal = 20.dp)
                    .padding(top = 8.dp)
            )
        }
        item { Spacer(modifier = Modifier.height(8.dp)) }
        itemsIndexed(items = histories) { index, history ->
            HistoryItem(
                text = history,
                onClick = { onHistoryClick(index) },
                onDelete = { onDeleteHistoryClick(index) },
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .animateItemPlacement()
            )
        }
        item { Spacer(modifier = Modifier.height(8.dp)) }
        if (histories.isNotEmpty()) {
            item {
                LargeButtonTertiary(
                    text = "See More",
                    onClick = onSeeMoreClick,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                )
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewHistoryContent() {
    ITTPizenTheme {
        Surface {
            val histories = listOf("abdul hafiz ramadan", "amita", "afifa")
            HistoryContent(
                histories = histories
            )
        }
    }
}
