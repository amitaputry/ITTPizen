package com.ta.ittpizen.ui.component.searchbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.component.text.TextBodyMedium
import com.ta.ittpizen.ui.component.textfield.BaseTextField
import com.ta.ittpizen.ui.theme.GreyShadow
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.SecondDarkGrey

val baseSearchBarColors @Composable get() = OutlinedTextFieldDefaults.colors(
    unfocusedBorderColor = Color.Transparent,
    focusedBorderColor = Color.Transparent,
    unfocusedContainerColor = GreyShadow,
    focusedContainerColor = GreyShadow
)

val baseSearchBarTextStyle @Composable get() = TextStyle(
    fontSize = 14.sp,
    color = MaterialTheme.colorScheme.onBackground,
    letterSpacing = 0.05.sp
)

@ExperimentalMaterial3Api
@Composable
fun BaseSearchBar(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    placeholder: String = "",
    enabled: Boolean = true,
    singleLine: Boolean = true,
    isError: Boolean = false,
) {
    BaseTextField(
        modifier = modifier.height(42.dp),
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        enabled = enabled,
        isError = isError,
        textStyle = baseSearchBarTextStyle,
        contentPadding = PaddingValues(horizontal = 5.dp, vertical = 10.dp),
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        },
        placeholder = {
            TextBodyMedium(text = placeholder, color = SecondDarkGrey)
        },
        colors = baseSearchBarColors
    )
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewBaseSearchBar() {
    ITTPizenTheme {
        Surface {
            var value by remember { mutableStateOf("") }
            BaseSearchBar(
                placeholder = "Find your friends..",
                value = value,
                onValueChange = { value = it },
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            )
        }
    }
}
