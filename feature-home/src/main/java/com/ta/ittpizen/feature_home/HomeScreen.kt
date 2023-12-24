package com.ta.ittpizen.feature_home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ta.ittpizen.ui.component.topappbar.HomeTopAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            HomeTopAppBar()
        },
        modifier = modifier
    ) { paddingValues ->

    }
}

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
