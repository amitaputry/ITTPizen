package com.ta.ittpizen.feature_job.add

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxkeppeker.sheets.core.CoreDialog
import com.maxkeppeker.sheets.core.models.CoreSelection
import com.maxkeppeker.sheets.core.models.base.SelectionButton
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.ta.ittpizen.data.remote.response.job.CreateJobResult
import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.model.preference.UserPreference
import com.ta.ittpizen.feature_job.component.AddJobDetailSection
import com.ta.ittpizen.feature_job.component.AddJobDoneSection
import com.ta.ittpizen.feature_job.component.AddJobFooter
import com.ta.ittpizen.feature_job.component.AddJobGeneralSection
import com.ta.ittpizen.feature_job.component.AddJobHeader
import com.ta.ittpizen.ui.component.text.TextBodyMedium
import com.ta.ittpizen.ui.component.text.TextTitleSmall
import com.ta.ittpizen.ui.component.topappbar.DetailTopAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.SecondDarkGrey
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun AddJobScreen(
    modifier: Modifier = Modifier,
    viewModel: AddJobViewModel = koinViewModel(),
    addJobGeneralViewModel: AddJobGeneralViewModel = koinViewModel(),
    addJobDetailViewModel: AddJobDetailViewModel = koinViewModel(),
    navigateUp: () -> Unit = {},
    navigateToDetailJob: (String) -> Unit = {},
) {

    val dialogState = rememberUseCaseState()

    var dialogTitle by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf("") }

    val steps = listOf("1. General", "2. Detail", "3. Done")

    val buttonNextGeneralEnabled by addJobGeneralViewModel.buttonNextGeneralEnabled.collectAsStateWithLifecycle(initialValue = false)
    val buttonPostDetailEnabled by addJobDetailViewModel.buttonPostDetailEnabled.collectAsStateWithLifecycle(initialValue = false)
    val buttonPrimaryLoading by viewModel.buttonLoading.collectAsStateWithLifecycle(initialValue = false)

    val userPreference by viewModel.userPreference.collectAsStateWithLifecycle(initialValue = UserPreference())
    val createJobResult by viewModel.createJobResult.collectAsStateWithLifecycle()

    val postUrl = "0"

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
            if (pagerState.currentPage < 1) {
                pagerState.animateScrollToPage(pagerState.currentPage+1)
            } else {
                val generalUiState = addJobGeneralViewModel.uiState.value
                val detailUiState = addJobDetailViewModel.uiState.value
                val token = userPreference.accessToken
                val title = generalUiState.jobTitle
                val company = generalUiState.companyName
                val street = generalUiState.street
                val city = generalUiState.city
                val province = generalUiState.province
                val workplaceType = generalUiState.workplaceType
                val jobType = generalUiState.jobType
                val description = detailUiState.description
                val skills = detailUiState.skills.filter { it.isNotEmpty() }
                val experience = detailUiState.experience
                val graduates = detailUiState.graduate
                val link = detailUiState.linkApplication

                viewModel.createJob(token, title, company, street, city, province, workplaceType, jobType, description, skills, experience, graduates, link)
            }
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

    LaunchedEffect(key1 = createJobResult) {
        if (createJobResult is Resource.Error) {
            dialogTitle = "Create job failed!"
            dialogMessage = (createJobResult as Resource.Error<CreateJobResult>).message ?: ""
            dialogState.show()
        }
        if (createJobResult is Resource.Success) {
            pagerState.animateScrollToPage(pagerState.pageCount)
        }
    }

    CoreDialog(
        state = dialogState,
        body = {
            TextTitleSmall(text = dialogTitle)
            Spacer(modifier = Modifier.height(16.dp))
            TextBodyMedium(text = dialogMessage, color = SecondDarkGrey)
        },
        selection = CoreSelection(
            negativeButton = null,
            positiveButton = SelectionButton(
                text = "Ok"
            ),
            onPositiveClick = dialogState::hide
        )
    )

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
                    primaryButtonLoading = buttonPrimaryLoading,
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
