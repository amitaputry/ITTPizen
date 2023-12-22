package com.ta.ittpizen.feature_auth.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ta.ittpizen.feature_auth.di.authModule
import com.ta.ittpizen.ui.component.button.LargePrimaryButton
import com.ta.ittpizen.ui.component.text.TextBodySmall
import com.ta.ittpizen.ui.component.textbutton.PrimaryTextButton
import com.ta.ittpizen.ui.component.textfield.DropDownTextFieldWithLabel
import com.ta.ittpizen.ui.component.textfield.OutlinedTextFieldWithLabel
import com.ta.ittpizen.ui.component.textfield.PasswordTextFieldWithLabel
import com.ta.ittpizen.ui.component.topappbar.DetailTopAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication

@ExperimentalMaterial3Api
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = koinViewModel(),
    navigateUp: () -> Unit = {},
    navigateToLoginScreen: () -> Unit = {}
) {
    val scrollState = rememberScrollState()

    val genders = listOf("Male", "Female")
    val status = listOf("Student", "Alumni", "Lecturer", "Staff", "Academic", "Organization")

    val uiState by viewModel.registerUiState.collectAsStateWithLifecycle()

    val fullName = uiState.fullName
    val studentIdOrLectureId = uiState.studentIdOrLectureId
    val email = uiState.email
    val phone = uiState.phone
    val gender = uiState.gender
    val currentStatus = uiState.status
    val password = uiState.password
    val confirmPassword = uiState.confirmPassword

    val buttonRegisterEnable by viewModel.buttonRegisterEnable.collectAsStateWithLifecycle(
        initialValue = false
    )

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Register",
                onNavigationClick = navigateUp
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextFieldWithLabel(
                label = "Full Name",
                value = fullName,
                onValueChange = viewModel::updateFullName,
                placeholder = "Enter your name",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextFieldWithLabel(
                label = "Student or lecturer ID Number",
                value = studentIdOrLectureId,
                onValueChange = viewModel::updateStudentIdOrLectureId,
                placeholder = "Enter your ID Number",
                isOptional = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextFieldWithLabel(
                label = "Email",
                value = email,
                onValueChange = viewModel::updateEmail,
                placeholder = "Enter your email",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextFieldWithLabel(
                label = "Phone",
                value = phone,
                onValueChange = viewModel::updatePhone,
                placeholder = "Enter your phone number",
                isOptional = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            DropDownTextFieldWithLabel(
                label = "Gender",
                selectedOption = gender,
                options = genders,
                onSelected = { viewModel.updateGender(genders[it]) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            DropDownTextFieldWithLabel(
                label = "Current Status",
                selectedOption = currentStatus,
                options = status,
                onSelected = { viewModel.updateStatus(status[it]) },
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
            PasswordTextFieldWithLabel(
                label = "Confirm Password",
                value = confirmPassword,
                onValueChange = viewModel::updateConfirmPassword,
                placeholder = "Enter your password",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            LargePrimaryButton(
                text = "Register",
                onClick = navigateToLoginScreen,
                enable = buttonRegisterEnable,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TextBodySmall(text = "Already have an account? ")
                PrimaryTextButton(
                    text = "Login",
                    fontSize = 12.sp,
                    onClick = navigateToLoginScreen
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewRegisterScreen() {
    KoinApplication(moduleList = { listOf(authModule) }) {
        ITTPizenTheme {
            Surface {
                RegisterScreen()
            }
        }
    }
}
