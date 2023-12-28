package com.ta.ittpizen.feature_job.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.component.button.PrimaryButton
import com.ta.ittpizen.ui.component.button.PrimaryOutlinedButton
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun AddJobFooter(
    modifier: Modifier = Modifier,
    primaryButtonText: String = "",
    secondaryButtonText: String = "",
    primaryButtonEnabled: Boolean = false,
    onPrimaryButtonClick: () -> Unit = {},
    onSecondaryButtonClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxWidth()
    ) {
        PrimaryOutlinedButton(
            text = secondaryButtonText,
            onClick = onSecondaryButtonClick,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(10.dp))
        PrimaryButton(
            text = primaryButtonText,
            onClick = onPrimaryButtonClick,
            enable = primaryButtonEnabled,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview
@Composable
fun PreviewAddJobFooter() {
    ITTPizenTheme {
        Surface {
            AddJobFooter()
        }
    }
}
