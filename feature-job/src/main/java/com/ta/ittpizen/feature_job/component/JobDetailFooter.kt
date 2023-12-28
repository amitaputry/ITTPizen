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
import com.ta.ittpizen.ui.component.button.LargePrimaryButton
import com.ta.ittpizen.ui.component.button.LargePrimaryOutlinedButton
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun JobDetailFooter(
    modifier: Modifier = Modifier,
    onApplyNowClick: () -> Unit = {},
    onSaveClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxWidth()
    ) {
        LargePrimaryButton(
            text = "Apply Now",
            onClick = onApplyNowClick,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(10.dp))
        LargePrimaryOutlinedButton(
            text = "Save",
            onClick = onSaveClick,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview
@Composable
fun PreviewJobDetailFooter() {
    ITTPizenTheme {
        Surface {
            JobDetailFooter()
        }
    }
}
