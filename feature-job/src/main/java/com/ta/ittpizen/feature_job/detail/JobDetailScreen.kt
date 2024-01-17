package com.ta.ittpizen.feature_job.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.model.job.DetailJobResult
import com.ta.ittpizen.domain.model.preference.UserPreference
import com.ta.ittpizen.feature_job.component.JobDetailContent
import com.ta.ittpizen.feature_job.component.JobDetailFooter
import com.ta.ittpizen.feature_job.component.JobDetailHeader
import com.ta.ittpizen.ui.component.topappbar.DetailTopAppBar
import com.ta.ittpizen.ui.theme.DisableColorGrey
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import org.koin.androidx.compose.koinViewModel

@ExperimentalMaterial3Api
@Composable
fun JobDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: JobDetailViewModel = koinViewModel(),
    navigateUp: () -> Unit = {},
    jobId: String = ""
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val snackBarHostState = remember { SnackbarHostState() }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val userPreference by viewModel.userPreference.collectAsStateWithLifecycle(initialValue = UserPreference())

    val detailJob = uiState.detailJob
    var detailJobData by remember { mutableStateOf(DetailJobResult()) }

    val buttonEnabled by viewModel.buttonEnabled.collectAsStateWithLifecycle(initialValue = false)
    val buttonSaveLoading by viewModel.buttonSaveLoading.collectAsStateWithLifecycle(initialValue = false)

    var jobSaved by remember { mutableStateOf(false) }

    val saveButtonText by remember(key1 = jobSaved) {
        val text = if (jobSaved) "Saved" else "Save"
        mutableStateOf(text)
    }

    val onApplyNowClick: () -> Unit = {
        val intentView = Intent(Intent.ACTION_VIEW, Uri.parse(detailJobData.link))
        context.startActivity(intentView)
    }

    val onSaveClick: () -> Unit = {
        val token = userPreference.accessToken
        if (jobSaved) {
            viewModel.unSaveJob(token, jobId)
        } else {
            viewModel.saveJob(token, jobId)
        }
        jobSaved = jobSaved.not()
    }

    LaunchedEffect(key1 = userPreference) {
        if (userPreference.accessToken.isEmpty()) return@LaunchedEffect
        viewModel.getJobById(token = userPreference.accessToken, jobId = jobId)
    }

    LaunchedEffect(key1 = detailJob) {
        if (detailJob is Resource.Success) {
            detailJobData = detailJob.data
            jobSaved = detailJob.data.saved
        }
    }

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Detail Job",
                onNavigationClick = navigateUp
            )
        },
        bottomBar = {
            JobDetailFooter(
                saveButtonText = saveButtonText,
                buttonEnabled = buttonEnabled,
                saveButtonLoading = buttonSaveLoading,
                onApplyNowClick = onApplyNowClick,
                onSaveClick = onSaveClick
            )
        },
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState)
        ) {
            JobDetailHeader(job = detailJobData, modifier = Modifier.padding(vertical = 20.dp))
            Divider(thickness = 0.5.dp, color = DisableColorGrey)
            JobDetailContent(job = detailJobData, modifier = Modifier.padding(vertical = 20.dp))
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewJobDetailScreen() {
    ITTPizenTheme {
        Surface {
            JobDetailScreen()
        }
    }
}
