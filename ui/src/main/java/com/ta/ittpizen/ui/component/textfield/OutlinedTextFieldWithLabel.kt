package com.ta.ittpizen.ui.component.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.component.text.TextTitleSmall
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalMaterial3Api
@Composable
fun OutlinedTextFieldWithLabel(
    modifier: Modifier = Modifier,
    label: String,
    value: String = "",
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    enabled: Boolean = true,
    singleLine: Boolean = true,
    isError: Boolean = false
) {
    Column(modifier = modifier) {
        TextTitleSmall(text = label, color = Color(0xFF343433))
        Spacer(modifier = Modifier.height(4.dp))
        BaseOutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
            enabled = enabled,
            singleLine = singleLine,
            isError = isError,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewOutlinedTextFieldWithLabel() {
    ITTPizenTheme {
        Surface {
            var value by remember { mutableStateOf("") }
            OutlinedTextFieldWithLabel(
                value = value,
                onValueChange = { value = it },
                label = "Full Name",
                modifier = Modifier.padding(16.dp)
                    .fillMaxWidth()
            )
        }
    }
}
