package com.ta.ittpizen.feature_chat.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.feature_chat.R
import com.ta.ittpizen.ui.component.iconbutton.FilledIconButton
import com.ta.ittpizen.ui.component.textfield.BaseOutlinedTextField
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.PrimaryRed

@ExperimentalMaterial3Api
@Composable
fun DetailChatFooter(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    placeholder: String = "",
    enabled: Boolean = true,
    isError: Boolean = false,
    onSendClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ) {
        BaseOutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
            enabled = enabled,
            isError = isError,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(10.dp))
        FilledIconButton(
            icon = painterResource(id = R.drawable.ic_send),
            contentDescription = "Send chat",
            onClick = onSendClick,
            containerColor = PrimaryRed
        )
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewDetailChatFooter() {
    ITTPizenTheme {
        Surface {
            var query by remember { mutableStateOf("") }
            DetailChatFooter(
                value = query,
                onValueChange = { query = it },
                placeholder = "Write the message",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}