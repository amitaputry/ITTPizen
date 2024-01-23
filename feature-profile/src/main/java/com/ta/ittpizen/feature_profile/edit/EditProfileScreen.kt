package com.ta.ittpizen.feature_profile.edit

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import com.ta.ittpizen.common.uriToFile
import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.model.preference.UserPreference
import com.ta.ittpizen.domain.model.profile.Profile
import com.ta.ittpizen.feature_profile.component.EditProfileBody
import com.ta.ittpizen.feature_profile.component.EditProfileHeader
import com.ta.ittpizen.ui.component.topappbar.DetailTopAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import org.koin.androidx.compose.koinViewModel

@ExperimentalMaterial3Api
@Composable
fun EditProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: EditProfileViewModel = koinViewModel(),
    userId: String = "",
    navigateUp: () -> Unit = {}
) {

    var photoFromGallery: Uri? by remember {
        mutableStateOf(null)
    }

    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
    ) { uri ->
        viewModel.updatePhoto("")
        photoFromGallery = uri
    }

    val photo = uiState.photo
    val name = uiState.name
    val type = uiState.type
    val studentId = uiState.studentOrLectureId

    val displayName = uiState.displayName
    val bio = uiState.bio
    val displayNameErrorName = uiState.displayNameErrorName

    val buttonEnabled by viewModel.buttonEnabled.collectAsStateWithLifecycle(initialValue = false)
    val buttonLoading by viewModel.buttonLoading.collectAsStateWithLifecycle(initialValue = false)
    val displayNameError by viewModel.displayNameError.collectAsStateWithLifecycle(initialValue = false)

    val userPreference by viewModel.userPreference.collectAsStateWithLifecycle(initialValue = UserPreference())
    val profile by viewModel.profile.collectAsStateWithLifecycle()
    val updateProfileResult by viewModel.updateProfileResult.collectAsStateWithLifecycle()

    val photoToShow = if (photoFromGallery != null) photoFromGallery else photo

    val updateDisplayName: (String) -> Unit = {
        val errorMessage = if (it.isNotEmpty()) "" else "Display name cannot be empty!"
        viewModel.updateDisplayName(it)
        viewModel.updateNameErrorMessage(errorMessage)
    }

    val launchImagePicker: () -> Unit = {
        photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    val onSaveClick: () -> Unit = {
        val photoFile = photoFromGallery?.uriToFile(context)
        viewModel.updateProfile(userPreference.accessToken, photoFile)
    }

    LaunchedEffect(key1 = userPreference) {
        if (userPreference.accessToken.isEmpty()) return@LaunchedEffect
        viewModel.getProfile(userPreference.accessToken)
    }
    LaunchedEffect(key1 = profile) {
        if (profile is Resource.Success) {
            val data = (profile as Resource.Success<Profile>).data
            viewModel.updateName(data.name)
            viewModel.updateType(data.type)
            viewModel.updateStudentOrLectureId("-")
            viewModel.updatePhoto(data.photo)
            viewModel.updateDisplayName(data.name)
            viewModel.updateBio(data.bio)
        }
    }
    LaunchedEffect(key1 = updateProfileResult) {
        if (updateProfileResult is Resource.Success) {
            val data = (updateProfileResult as Resource.Success<Profile>).data
            val updateUserPreference = userPreference.copy(
                photo = data.photo,
                name = data.name,
                type = data.type
            )
            viewModel.updateUserPreference(updateUserPreference)
            Toast.makeText(context, "Update profile successfully!", Toast.LENGTH_SHORT).show()
        }
        if (updateProfileResult is Resource.Error) {
            val message = (updateProfileResult as Resource.Error<Profile>).message
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
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
            if (photoToShow != null) {
                EditProfileHeader(
                    photo = photoToShow,
                    name = name,
                    type = type,
                    studentId = studentId,
                    onPickImageClick = launchImagePicker
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            EditProfileBody(
                displayName = displayName,
                bio = bio,
                onDisplayNameChanged = updateDisplayName,
                onBioChanged = viewModel::updateBio,
                displayNameError = displayNameError,
                displayNameErrorMessage = displayNameErrorName,
                buttonSaveEnabled = buttonEnabled,
                buttonSaveLoading = buttonLoading,
                onSaveClick = onSaveClick
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
