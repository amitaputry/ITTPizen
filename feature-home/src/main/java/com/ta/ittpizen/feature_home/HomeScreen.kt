package com.ta.ittpizen.feature_home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.ta.ittpizen.domain.entity.PostItem
import com.ta.ittpizen.ui.component.post.PostItem
import com.ta.ittpizen.ui.component.tab.BaseScrollableTabRow
import com.ta.ittpizen.ui.component.topappbar.HomeTopAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {

    val tabs = listOf("All Post", "Tweet", "Academic", "#PrestasiITTP", "Events", "Scholarship")

    val pagerState = rememberPagerState { tabs.size }
    val scope = rememberCoroutineScope()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val selectedTabIndex by remember(key1 = pagerState.currentPage) {
        mutableIntStateOf(pagerState.currentPage)
    }

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

    val onTabSelected: (Int) -> Unit = {
        scope.launch {
            pagerState.scrollToPage(it)
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            HomeTopAppBar(
                modifier = Modifier
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
                    .zIndex(1f)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .zIndex(2f)
        ) {
            BaseScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                tabs = tabs,
                onSelected = onTabSelected,
                edgePadding = 20.dp,
                modifier = Modifier.padding(vertical = 10.dp)
            )
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                LazyColumn {
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
