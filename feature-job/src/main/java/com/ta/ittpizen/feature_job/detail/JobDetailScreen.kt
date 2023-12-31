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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.domain.utils.DataJobDetail
import com.ta.ittpizen.domain.utils.DataJobItem
import com.ta.ittpizen.feature_job.component.JobDetailContent
import com.ta.ittpizen.feature_job.component.JobDetailFooter
import com.ta.ittpizen.feature_job.component.JobDetailHeader
import com.ta.ittpizen.ui.component.topappbar.DetailTopAppBar
import com.ta.ittpizen.ui.theme.DisableColorGrey
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun JobDetailScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
    jobId: String = ""
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    var job by remember { mutableStateOf(DataJobDetail.getJobDetailById(jobId)) }

    if (job == null) return

    var saveButtonText by remember(key1 = job) {
        val text = if (job?.saved == true) "Saved" else "Save"
        mutableStateOf(text)
    }

    val onApplyNowClick: () -> Unit = {
        val intentView = Intent(Intent.ACTION_VIEW, Uri.parse(job!!.link))
        context.startActivity(intentView)
    }

    val onSaveClick: () -> Unit = {
        val jobItem = DataJobItem.getJobItemById(jobId)
        if (jobItem != null) {
            scope.launch {
                val updatedJobItem = DataJobItem.savedOrUnsavedJob(jobItem)
                job = job?.copy(
                    saved = updatedJobItem?.saved ?: false
                )
                if (updatedJobItem?.saved == false) {
                    snackBarHostState.showSnackbar("Job delete from saved job!")
                } else {
                    snackBarHostState.showSnackbar("Job saved!")
                }
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        val jobItem = DataJobItem.getJobItemById(jobId)
        saveButtonText = if (jobItem?.saved == true) "Saved" else "Save"
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
            JobDetailHeader(job = job!!, modifier = Modifier.padding(vertical = 20.dp))
            Divider(thickness = 0.5.dp, color = DisableColorGrey)
            JobDetailContent(job = job!!, modifier = Modifier.padding(vertical = 20.dp))
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
