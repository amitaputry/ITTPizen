package com.ta.ittpizen.feature_post.add

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.maxkeppeker.sheets.core.CoreDialog
import com.maxkeppeker.sheets.core.models.CoreSelection
import com.maxkeppeker.sheets.core.models.base.SelectionButton
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.ta.ittpizen.common.uriToFile
import com.ta.ittpizen.domain.model.Resource
import com.ta.ittpizen.domain.model.post.CreatePostResult
import com.ta.ittpizen.domain.model.preference.UserPreference
import com.ta.ittpizen.feature_post.component.AddPostFooter
import com.ta.ittpizen.feature_post.component.AddPostHeader
import com.ta.ittpizen.ui.component.iconbutton.BaseIconButton
import com.ta.ittpizen.ui.component.text.TextBodyMedium
import com.ta.ittpizen.ui.component.text.TextTitleSmall
import com.ta.ittpizen.ui.component.textfield.BaseTextField
import com.ta.ittpizen.ui.component.topappbar.DetailTopAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.SecondDarkGrey
import org.koin.androidx.compose.koinViewModel

enum class AddPostType(val title: String) {
    TWEET("Tweet"),
    ACADEMIC("Academic"),
    ACHIEVEMENT("Achievement"),
    EVENT("Event"),
    SCHOLARSHIP("Scholarship"),
}

@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Composable
fun AddPostScreen(
    modifier: Modifier = Modifier,
    viewModel: AddPostViewModel = koinViewModel(),
    navigateUp: () -> Unit = {},
    navigateToSuccessAddPostScreen: (String) -> Unit = {},
    type: AddPostType = AddPostType.TWEET
) {

    val context = LocalContext.current
    val dialogState = rememberUseCaseState()

    var dialogTitle by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf("") }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val userPreference by viewModel.userPreference.collectAsStateWithLifecycle(initialValue = UserPreference())

    val media = uiState.media
    val text = uiState.text

    val buttonEnabled by viewModel.buttonEnabled.collectAsStateWithLifecycle(initialValue = false)
    val buttonLoading by viewModel.buttonLoading.collectAsStateWithLifecycle(initialValue = false)
    
    val createPostResult by viewModel.createPostResult.collectAsStateWithLifecycle()

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
    ) { uri ->
        viewModel.updateMedia(uri)
    }

    val topAppBarTitle = "Posting ${type.title}"

    val launchImagePicker: () -> Unit = {
        photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    val clearPhotoGallery: () -> Unit = {
        viewModel.updateMedia(null)
    }

    val onPostClick: () -> Unit = {
        if (userPreference.accessToken.isNotEmpty()) {
            val token = userPreference.accessToken
            val mediaFile = media?.uriToFile(context)
            viewModel.createPost(token, mediaFile)
        }
    }

    LaunchedEffect(key1 = type) {
        viewModel.updateType(type.title)
    }
    
    LaunchedEffect(key1 = createPostResult) {
        if (createPostResult is Resource.Error) {
            dialogTitle = "Create post failed!"
            dialogMessage = (createPostResult as Resource.Error<CreatePostResult>).message ?: ""
            dialogState.show()
        }
        if (createPostResult is Resource.Success) {
            val postId = (createPostResult as Resource.Success<CreatePostResult>).data.id
            navigateToSuccessAddPostScreen(postId)
        }
    }

    CoreDialog(
        state = dialogState,
        body = {
            TextTitleSmall(text = dialogTitle)
            Spacer(modifier = Modifier.height(16.dp))
            TextBodyMedium(text = dialogMessage, color = SecondDarkGrey)
        },
        selection = CoreSelection(
            negativeButton = null,
            positiveButton = SelectionButton(
                text = "Ok"
            ),
            onPositiveClick = dialogState::hide
        )
    )

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = topAppBarTitle,
                onNavigationClick = navigateUp
            )
        },
        bottomBar = {
            AddPostFooter(
                type = type,
                postEnabled = buttonEnabled,
                postLoading = buttonLoading,
                onAddPhotoClick = launchImagePicker,
                onPostClick = onPostClick
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .animateContentSize()
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            AddPostHeader(
                name = userPreference.name,
                type = userPreference.type,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            BaseTextField(
                value = text,
                onValueChange = viewModel::updateText,
                placeholder = "What story do you want to talk about?",
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
            AnimatedVisibility(visible = media != null) {
                val model = ImageRequest.Builder(context)
                    .data(media)
                    .crossfade(true)
                    .build()
                Box(
                    modifier = Modifier
                        .padding(start = 38.dp, top = 20.dp, end = 38.dp, bottom = 40.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .fillMaxHeight(0.7f)
                        .fillMaxWidth()
                ) {
                    AsyncImage(
                        model = model,
                        contentDescription = "profile",
//                        placeholder = painterResource(id = com.ta.ittpizen.ui.R.drawable.img_media_placeholder),
//                        error = painterResource(id = com.ta.ittpizen.ui.R.drawable.img_media_placeholder),
                        contentScale = ContentScale.Crop
                    )
                    BaseIconButton(
                        icon = painterResource(id = com.ta.ittpizen.ui.R.drawable.ic_close),
                        onClick = clearPhotoGallery,
                        modifier = Modifier
                            .padding(10.dp)
                            .align(Alignment.TopEnd)
                    )
                }
            }
        }
    }
}

@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewAddPostScreen() {
    ITTPizenTheme {
        Surface {
            AddPostScreen(
                type = AddPostType.ACHIEVEMENT
            )
        }
    }
}
