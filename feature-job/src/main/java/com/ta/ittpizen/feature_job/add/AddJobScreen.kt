package com.ta.ittpizen.feature_job.add

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ta.ittpizen.feature_job.component.AddJobDetailSection
import com.ta.ittpizen.feature_job.component.AddJobDoneSection
import com.ta.ittpizen.feature_job.component.AddJobFooter
import com.ta.ittpizen.feature_job.component.AddJobGeneralSection
import com.ta.ittpizen.feature_job.component.AddJobHeader
import com.ta.ittpizen.ui.component.topappbar.DetailTopAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun AddJobScreen(
    modifier: Modifier = Modifier,
    addJobGeneralViewModel: AddJobGeneralViewModel = koinViewModel(),
    addJobDetailViewModel: AddJobDetailViewModel = koinViewModel(),
    navigateUp: () -> Unit = {},
    navigateToDetailJob: (String) -> Unit = {},
) {

    val steps = listOf("1. General", "2. Detail", "3. Done")

    val buttonNextGeneralEnabled by addJobGeneralViewModel.buttonNextGeneralEnabled.collectAsStateWithLifecycle(initialValue = false)
    val buttonPostDetailEnabled by addJobDetailViewModel.buttonPostDetailEnabled.collectAsStateWithLifecycle(initialValue = false)

    val postUrl = "new-job-id"

    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState(pageCount = steps::size)
    val scope = rememberCoroutineScope()

    val activeStep by remember(key1 = pagerState.currentPage) {
        mutableStateOf(steps[pagerState.currentPage])
    }

    val primaryButton by remember(key1 = pagerState.currentPage) {
        val text = if (pagerState.currentPage == 1) "Post" else "Next"
        mutableStateOf(text)
    }
    val secondaryButton by remember(key1 = pagerState.currentPage) {
        val text = if (pagerState.currentPage == 0) "Cancel" else "Back"
        mutableStateOf(text)
    }
    val primaryButtonEnabled by remember {
        derivedStateOf {
            if (pagerState.currentPage == 0) {
                buttonNextGeneralEnabled
            } else {
                buttonPostDetailEnabled
            }
        }
    }

    val onButtonPrimaryClick: () -> Unit = {
        scope.launch {
            pagerState.animateScrollToPage(pagerState.currentPage+1)
        }
    }
    val onButtonSecondClick: () -> Unit = {
        scope.launch {
            if (pagerState.currentPage == 0) {
                navigateUp()
            } else {
                pagerState.animateScrollToPage(0)
            }
        }
    }

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Posting Job",
                onNavigationClick = navigateUp
            )
        },
        bottomBar = {
            AnimatedVisibility(
                visible = pagerState.currentPage != steps.lastIndex,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                AddJobFooter(
                    primaryButtonText = primaryButton,
                    primaryButtonEnabled = primaryButtonEnabled,
                    onPrimaryButtonClick = onButtonPrimaryClick,
                    secondaryButtonText = secondaryButton,
                    onSecondaryButtonClick = onButtonSecondClick
                )
            }
        },
        modifier = modifier
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            AddJobHeader(
                steps = steps,
                activeStep = activeStep,
                modifier = Modifier.padding(all = 20.dp)
            )
            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false,
                verticalAlignment = Alignment.Top,
                modifier = Modifier.weight(1f)
            ) { page ->
                when (page) {
                    0 -> AddJobGeneralSection(
                        viewModel = addJobGeneralViewModel,
                        modifier = Modifier
                            .verticalScroll(scrollState)
                            .padding(horizontal = 20.dp)
                            .padding(bottom = 20.dp)
                    )
                    1 -> AddJobDetailSection(
                        viewModel = addJobDetailViewModel,
                        modifier = Modifier
                            .verticalScroll(scrollState)
                            .padding(horizontal = 20.dp)
                    )
                    2 -> AddJobDoneSection(
                        onSeePostClick = { navigateToDetailJob(postUrl) },
                        onFinishClick = navigateUp,
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewAddJobScreen() {
    ITTPizenTheme {
        Surface {
            AddJobScreen()
        }
    }
}
