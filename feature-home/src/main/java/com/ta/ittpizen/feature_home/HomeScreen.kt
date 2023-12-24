package com.ta.ittpizen.feature_home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.domain.entity.PostItem
import com.ta.ittpizen.ui.component.chip.SingleChipRow
import com.ta.ittpizen.ui.component.post.PostItem
import com.ta.ittpizen.ui.component.tab.BaseScrollableTabRow
import com.ta.ittpizen.ui.component.topappbar.HomeTopAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalFoundationApi
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val tabs = listOf("All Post", "Tweet", "Academic", "#PrestasiITTP", "Events", "Scholarship")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val postItems = (1..10).map {
        PostItem(
            id = it.toString(),
            name = "Amita Putry Prasasti",
            type = "Student",
            date = "1 hours ago",
            profile = "",
            text = "Haloo, salam kenal, mari saling koneksi temen-temen. Haloo, salam kenal, mari saling koneksi temen-temen. Haloo, salam kenal, mari saling koneksi temen-temen. Haloo, salam kenal, mari saling koneksi temen-temen",
//            media = "https://kemahasiswaan.ittelkom-pwt.ac.id/wp-content/uploads/sites/27/2021/05/pilmapres20211-1200x675.png",
            liked = true
        )
    }

    Scaffold(modifier = modifier) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            item {
                HomeTopAppBar()
            }
            stickyHeader {
                BaseScrollableTabRow(
                    selectedTabIndex = selectedTabIndex,
                    tabs = tabs,
                    onSelected = { selectedTabIndex = it },
                    edgePadding = 20.dp,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            }
            items(items = postItems, key = { it.id }) {
                PostItem(
                    post = it,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .padding(horizontal = 20.dp)
                )
            }
        }
    }
}

@ExperimentalLayoutApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewHomeScreen() {
    ITTPizenTheme {
        Surface {
            HomeScreen()
        }
    }
}
