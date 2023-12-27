package com.ta.ittpizen.feature_job.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.feature_job.component.AddJobFooter
import com.ta.ittpizen.feature_job.component.AddJobHeader
import com.ta.ittpizen.feature_job.component.JobDetailFooter
import com.ta.ittpizen.ui.component.topappbar.DetailTopAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalMaterial3Api
@Composable
fun AddJobScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {}
) {

    val scrollState = rememberScrollState()

    var primaryButton by remember { mutableStateOf("Next") }
    var secondaryButton by remember { mutableStateOf("Cancel") }

    val steps = listOf("1. General", "2. Detail", "3. Done")
    val activeStep = steps[0]

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Posting Job",
                onNavigationClick = navigateUp
            )
        },
        bottomBar = {
            AddJobFooter(
                primaryButtonText = primaryButton,
                secondaryButtonText = secondaryButton
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            AddJobHeader(
                steps = steps,
                activeStep = activeStep,
                modifier = Modifier.padding(20.dp)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(scrollState)
            ) {
                Text(text = "Test", modifier = Modifier.padding(20.dp))
            }
        }
    }
}

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
