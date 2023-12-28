package com.ta.ittpizen.ui.component.searchbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.component.iconbutton.BaseIconButton
import com.ta.ittpizen.ui.component.textfield.BaseTextField
import com.ta.ittpizen.ui.theme.ColorText
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.PrimaryRed
import com.ta.ittpizen.ui.theme.SecondDarkGrey

val outlinedSearchBarColors @Composable get() = OutlinedTextFieldDefaults.colors(
    unfocusedBorderColor = SecondDarkGrey,
    focusedBorderColor = PrimaryRed,
    unfocusedContainerColor = Color.Transparent,
    focusedContainerColor = Color.Transparent
)

val outlinedSearchBarTextStyle @Composable get() = TextStyle(
    fontSize = 14.sp,
    color = ColorText,
    letterSpacing = 0.05.sp
)

@ExperimentalMaterial3Api
@Composable
fun OutlinedSearchBar(
    modifier: Modifier = Modifier,
    query: String = "",
    onQueryChange: (String) -> Unit = {},
    placeholder: String = "",
    enabled: Boolean = true,
    isError: Boolean = false,
    onSearchClick: () -> Unit = {}
) {
    BaseTextField(
        modifier = modifier.height(42.dp),
        value = query,
        onValueChange = onQueryChange,
        singleLine = true,
        enabled = enabled,
        isError = isError,
        textStyle = outlinedSearchBarTextStyle,
        contentPadding = PaddingValues(horizontal = 5.dp, vertical = 10.dp),
        placeholder = placeholder,
        colors = outlinedSearchBarColors,
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        },
        trailingIcon = {
            AnimatedVisibility(
                visible = query.isNotEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                BaseIconButton(
                    icon = painterResource(id = R.drawable.ic_clear),
                    onClick = { onQueryChange("") }
                )
            }
        },
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words, imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearchClick() })
    )
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewOutlinedSearchBar() {
    ITTPizenTheme {
        Surface {
            var value by remember { mutableStateOf("Text") }
            OutlinedSearchBar(
                placeholder = "Find your friends..",
                query = value,
                onQueryChange = { value = it },
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            )
        }
    }
}
