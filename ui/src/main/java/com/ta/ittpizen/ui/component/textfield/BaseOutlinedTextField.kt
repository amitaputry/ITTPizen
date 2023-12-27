package com.ta.ittpizen.ui.component.textfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ta.ittpizen.ui.component.text.TextBodyMedium
import com.ta.ittpizen.ui.component.text.TextBodySmall
import com.ta.ittpizen.ui.theme.ColorText
import com.ta.ittpizen.ui.theme.Grey
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.PrimaryRed

val baseOutlinedTextFieldColors @Composable get() = OutlinedTextFieldDefaults.colors(
    unfocusedBorderColor = Color(0xFFDEDEDE),
    focusedBorderColor = PrimaryRed
)

val baseOutlinedTextFieldTextStyle @Composable get() = TextStyle(
    fontSize = 14.sp,
    lineHeight = 18.sp,
    color = ColorText,
    letterSpacing = 0.04.sp,
)

@ExperimentalMaterial3Api
@Composable
fun BaseOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = baseOutlinedTextFieldTextStyle,
    placeholder: String = "",
    label: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    supportingText: String? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    minLines: Int = Int.MAX_VALUE,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = RoundedCornerShape(100.dp),
    colors: TextFieldColors = baseOutlinedTextFieldColors,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 10.dp)
) {
    val cursorColor = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
    val supportingTextColor = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onBackground
    val supportingTextComposable: @Composable (() -> Unit)? = if (true) {
        { TextBodySmall(text = "supportingText", color = supportingTextColor) }
    } else null

    BasicTextField(
        value = value,
        modifier = modifier.height(45.dp),
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        cursorBrush = SolidColor(cursorColor),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        singleLine = singleLine,
        minLines = minLines,
        maxLines = maxLines,
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.run {
                OutlinedTextFieldDefaults.DecorationBox(
                    value = value,
                    innerTextField = innerTextField,
                    enabled = enabled,
                    singleLine = singleLine,
                    visualTransformation = visualTransformation,
                    interactionSource = interactionSource,
                    isError = isError,
                    label = label,
                    placeholder = {
                        TextBodyMedium(text = placeholder, color = Grey)
                    },
                    supportingText = supportingTextComposable,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon,
                    colors = colors,
                    contentPadding = contentPadding,
                    container = {
                        OutlinedTextFieldDefaults.ContainerBox(
                            enabled = enabled,
                            isError = isError,
                            interactionSource = interactionSource,
                            colors = colors,
                            shape = shape,
                            focusedBorderThickness = OutlinedTextFieldDefaults.FocusedBorderThickness,
                            unfocusedBorderThickness = OutlinedTextFieldDefaults.UnfocusedBorderThickness,
                        )
                    },
                )
            }
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
                placeholder = "Enter your username",
                isError = true,
                supportingText = "Hello Mas Bro",
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}
