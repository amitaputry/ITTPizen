package com.ta.ittpizen.ui.component.textfield

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ta.ittpizen.ui.component.text.TextBodyMedium
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalMaterial3Api
@Composable
fun BaseOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    enabled: Boolean = true,
    singleLine: Boolean = true,
    isError: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
) {
    BaseTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        enabled = enabled,
        isError = isError,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        supportingText = supportingText,
        placeholder = {
            TextBodyMedium(text = placeholder, color = Color(0xFFAAAAAA))
        }
    )
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewBaseOutlinedTextField() {
    ITTPizenTheme {
        Surface {
            var value by remember { mutableStateOf("") }
            BaseOutlinedTextField(
                value = value,
                onValueChange = { value = it },
                placeholder = "Enter your username"
            )
        }
    }
}
