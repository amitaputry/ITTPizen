package com.ta.ittpizen.feature_job.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun AddJobHeader(
    modifier: Modifier = Modifier,
    steps: List<String> = emptyList(),
    activeStep: String = ""
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        steps.forEach { step ->
            val active = step == activeStep
            AddJobStepItem(
                step = step,
                active = active,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview
@Composable
fun PreviewAddJobHeader() {
    ITTPizenTheme {
        Surface {
            val steps = listOf("1. General", "2. Detail", "3. Done")
            val activeStep = steps[0]
            AddJobHeader(
                steps = steps,
                activeStep = activeStep,
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}
