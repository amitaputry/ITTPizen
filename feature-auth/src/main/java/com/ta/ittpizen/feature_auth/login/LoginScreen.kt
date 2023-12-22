package com.ta.ittpizen.feature_auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ta.ittpizen.feature_auth.R
import com.ta.ittpizen.feature_auth.di.authModule
import com.ta.ittpizen.ui.component.button.LargePrimaryButton
import com.ta.ittpizen.ui.component.text.TextBodySmall
import com.ta.ittpizen.ui.component.text.TextTitleLarge
import com.ta.ittpizen.ui.component.textbutton.PrimaryTextButton
import com.ta.ittpizen.ui.component.textfield.OutlinedTextFieldWithLabel
import com.ta.ittpizen.ui.component.textfield.PasswordTextFieldWithLabel
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.PrimaryRed
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = koinViewModel(),
    navigateToRegisterScreen: () -> Unit = {}
) {
    val uiState by viewModel.registerUiState.collectAsStateWithLifecycle()

    val email = uiState.email
    val password = uiState.password

    val emailError by viewModel.emailError.collectAsStateWithLifecycle(initialValue = false)

    val buttonRegisterEnable by viewModel.buttonRegisterEnable.collectAsStateWithLifecycle(
        initialValue = false
    )

    Scaffold(modifier = modifier) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.img_login),
                contentDescription = null,
                modifier = Modifier.weight(1f),
                contentScale = ContentScale.FillHeight
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextTitleLarge(
                text = "Login",
                color = PrimaryRed
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextBodySmall(text = "Hi ITTPizens, Enter your credentials and let’s started")
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextFieldWithLabel(
                label = "Email",
                value = email,
                isError = emailError,
                onValueChange = viewModel::updateEmail,
                placeholder = "Enter your email",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            PasswordTextFieldWithLabel(
                label = "Password",
                value = password,
                onValueChange = viewModel::updatePassword,
                placeholder = "Enter your password",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            LargePrimaryButton(
                text = "Login",
                onClick = {},
                enable = buttonRegisterEnable,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TextBodySmall(text = "Do not have an account? ")
                PrimaryTextButton(
                    text = "Register",
                    fontSize = 12.sp,
                    onClick = navigateToRegisterScreen
                )
            }
            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewLoginScreen() {
    KoinApplication(moduleList = { listOf(authModule) }) {
        ITTPizenTheme {
            Surface {
                LoginScreen()
            }
        }
    }
}
