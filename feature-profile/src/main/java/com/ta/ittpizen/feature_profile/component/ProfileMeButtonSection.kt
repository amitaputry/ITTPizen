package com.ta.ittpizen.feature_profile.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.component.button.LargePrimaryButton
import com.ta.ittpizen.ui.component.iconbutton.OutlinedIconButton
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun ProfileMeButtonSection(
    modifier: Modifier = Modifier,
    onEditProfileClick: () -> Unit = {},
    onSavedJobClick: () -> Unit = {}
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LargePrimaryButton(
            text = "Edit Profile",
            onClick = onEditProfileClick,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(10.dp))
        OutlinedIconButton(
            icon = painterResource(id = com.ta.ittpizen.ui.R.drawable.ic_saved_primary),
            onClick = onSavedJobClick
        )
    }
}

@Preview
@Composable
fun PreviewProfileMeButtonSection() {
    ITTPizenTheme {
        Surface {
            ProfileMeButtonSection(
                modifier = Modifier.padding(all = 20.dp)
            )
        }
    }
}
