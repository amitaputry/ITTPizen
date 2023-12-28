package com.ta.ittpizen.feature_profile.component

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
import com.ta.ittpizen.ui.component.button.LargePrimaryButton
import com.ta.ittpizen.ui.component.button.LargePrimaryOutlinedButton
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun ProfileFriendButtonSection(
    modifier: Modifier = Modifier,
    primaryText: String = "",
    secondaryText: String = "",
    onPrimaryClick: () -> Unit = {},
    onSecondaryClick: () -> Unit = {}
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LargePrimaryButton(
            text = primaryText,
            onClick = onPrimaryClick,
            modifier = Modifier.weight(1f)
        )
        LargePrimaryOutlinedButton(
            text = secondaryText,
            onClick = onSecondaryClick,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview
@Composable
fun PreviewProfileFriendButtonSection() {
    ITTPizenTheme {
        Surface {
            ProfileFriendButtonSection(
                primaryText = "Connect",
                secondaryText = "Message",
                modifier = Modifier.padding(all = 20.dp)
            )
        }
    }
}
