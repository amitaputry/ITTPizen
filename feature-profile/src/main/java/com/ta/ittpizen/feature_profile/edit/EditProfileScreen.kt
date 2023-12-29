package com.ta.ittpizen.feature_profile.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ta.ittpizen.feature_profile.component.EditProfileBody
import com.ta.ittpizen.feature_profile.component.EditProfileHeader
import com.ta.ittpizen.ui.component.topappbar.DetailTopAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import org.koin.androidx.compose.koinViewModel

@ExperimentalMaterial3Api
@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    userId: String = "",
    navigateUp: () -> Unit = {},
    viewModel: EditProfileViewModel = koinViewModel()
) {

    val scrollState = rememberScrollState()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val photo = uiState.name
    val name = uiState.name
    val type = uiState.type
    val studentId = uiState.studentId

    val displayName = uiState.displayName
    val bio = uiState.bio
    val displayNameErrorName = uiState.displayNameErrorName

    val buttonEnabled by viewModel.buttonEnable.collectAsStateWithLifecycle(initialValue = false)

    val displayNameError by viewModel.displayNameError.collectAsStateWithLifecycle(initialValue = false)

    val updateDisplayName: (String) -> Unit = {
        val errorMessage = if (it.isNotEmpty()) "" else "Display name cannot be empty!"
        viewModel.updateDisplayName(it)
        viewModel.updateNameErrorMessage(errorMessage)
    }

    Scaffold(
        topBar = {
            DetailTopAppBar(
                onNavigationClick = navigateUp
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            EditProfileHeader(
                photo = photo,
                name = name,
                type = type,
                studentId = studentId
            )
            Spacer(modifier = Modifier.height(20.dp))
            EditProfileBody(
                displayName = displayName,
                bio = bio,
                onDisplayNameChanged = updateDisplayName,
                onBioChanged = viewModel::updateBio,
                displayNameError = displayNameError,
                displayNameErrorMessage = displayNameErrorName,
                buttonSaveEnabled = buttonEnabled,
                onSaveClick = navigateUp
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewEditProfileScreen() {
    ITTPizenTheme {
        Surface {
            EditProfileScreen()
        }
    }
}
