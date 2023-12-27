package com.ta.ittpizen.ui.component.textfield

import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalMaterial3Api
@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    supportingText: String? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    isError: Boolean = false,
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val visualTransformation = remember(key1 = passwordVisible) {
        if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
    }
    val passwordToggleIcon = remember(key1 = passwordVisible) {
        if (passwordVisible) R.drawable.ic_visible_on else R.drawable.ic_visible_off
    }
    val togglePassword: () -> Unit = {
        passwordVisible = !passwordVisible
    }
    BaseOutlinedTextField(
        modifier = modifier.height(45.dp),
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        enabled = enabled,
        isError = isError,
        placeholder = placeholder,
        supportingText = supportingText,
        visualTransformation = visualTransformation,
        trailingIcon = {
            IconButton(onClick = togglePassword) {
                Icon(
                    painter = painterResource(id = passwordToggleIcon),
                    contentDescription = "Toggle Password"
                )
            }
        }
    )
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewPasswordTextField() {
    ITTPizenTheme {
        Surface {
            var value by remember { mutableStateOf("") }
            PasswordTextField(
                value = value,
                onValueChange = { value = it },
                placeholder = "Enter your username"
            )
        }
    }
}
