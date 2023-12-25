package com.ta.ittpizen.feature_post.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.feature_post.component.AddPostFooter
import com.ta.ittpizen.feature_post.component.AddPostHeader
import com.ta.ittpizen.ui.component.textfield.BaseTextField
import com.ta.ittpizen.ui.component.topappbar.DetailTopAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme

enum class AddPostType(val title: String) {
    TWEET("Tweet"),
    ACADEMIC("Academic"),
    ACHIEVEMENT("Achievement"),
    EVENT("Event"),
    SCHOLARSHIP("Scholarship"),
}

@ExperimentalMaterial3Api
@Composable
fun AddPostScreen(
    modifier: Modifier = Modifier,
    type: AddPostType = AddPostType.TWEET
) {
    val topAppBarTitle = "Posting ${type.title}"

    val userName = "Amita Putry Prasasti"
    val userType = "Student"

    var text by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            DetailTopAppBar(title = topAppBarTitle)
        },
        bottomBar = {
            AddPostFooter(
                type = type,
                onAddPhotoClick = {},
                onPostClick = {}
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .imePadding()
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            AddPostHeader(
                name = userName,
                type = userType,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            BaseTextField(
                value = text,
                onValueChange = { text = it },
                placeholder = "What story do you want to talk about?",
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
        }
    }
}

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
