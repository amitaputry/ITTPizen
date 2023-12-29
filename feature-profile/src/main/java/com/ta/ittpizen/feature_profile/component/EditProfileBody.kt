package com.ta.ittpizen.feature_profile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.component.button.LargePrimaryButton
import com.ta.ittpizen.ui.component.textfield.OutlinedTextFieldWithLabel
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalMaterial3Api
@Composable
fun EditProfileBody(
    modifier: Modifier = Modifier,
    displayName: String = "",
    bio: String = "",
    onDisplayNameChanged: (String) -> Unit = {},
    onBioChanged: (String) -> Unit = {},
    displayNameError: Boolean = false,
    displayNameErrorMessage: String = "",
    buttonSaveEnabled: Boolean = false,
    buttonSaveLoading: Boolean = false,
    onSaveClick: () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        OutlinedTextFieldWithLabel(
            label = "Display Name",
            value = displayName,
            onValueChange = onDisplayNameChanged,
            placeholder = "Enter your name",
            isError = displayNameError,
            supportingText = displayNameErrorMessage
        )
        OutlinedTextFieldWithLabel(
            label = "Short Description (bio)",
            value = bio,
            onValueChange = onBioChanged,
            placeholder = "Short Description (bio)",
            minLines = 6,
            singleLine = false
        )
        LargePrimaryButton(
            text = "Save",
            modifier = Modifier.fillMaxWidth(),
            onClick = onSaveClick,
            enable = buttonSaveEnabled,
            loading = buttonSaveLoading
        )
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewEditProfileBody() {
    ITTPizenTheme {
        Surface {
            EditProfileBody(
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}
