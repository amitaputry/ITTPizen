package com.ta.ittpizen.feature_job.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ta.ittpizen.feature_job.add.AddJobGeneralViewModel
import com.ta.ittpizen.ui.component.text.TextBodyLarge
import com.ta.ittpizen.ui.component.textfield.DropDownTextFieldWithLabel
import com.ta.ittpizen.ui.component.textfield.OutlinedTextFieldWithLabel
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import org.koin.androidx.compose.koinViewModel

@ExperimentalMaterial3Api
@Composable
fun AddJobGeneralSection(
    modifier: Modifier = Modifier,
    viewModel: AddJobGeneralViewModel = koinViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val workplaceTypes = listOf("Onsite", "Remote")
    val jobTypes = listOf("FullTime", "PartTime", "Contract", "Volunteer", "Internship")

    val jobTitle = uiState.jobTitle
    val companyName = uiState.companyName
    val street = uiState.street
    val city = uiState.city
    val province = uiState.province
    val workplaceType = uiState.workplaceType
    val jobType = uiState.jobType

    val jobTitleErrorMessage = uiState.jobTitleErrorMessage
    val companyNameErrorMessage = uiState.companyNameErrorMessage
    val streetErrorMessage = uiState.streetErrorMessage
    val cityErrorMessage = uiState.cityErrorMessage
    val provinceErrorMessage = uiState.provinceErrorMessage

    val jobTitleError by viewModel.jobTitleError.collectAsStateWithLifecycle(initialValue = false)
    val companyNameError by viewModel.companyNameError.collectAsStateWithLifecycle(initialValue = false)
    val streetError by viewModel.streetError.collectAsStateWithLifecycle(initialValue = false)
    val cityError by viewModel.cityError.collectAsStateWithLifecycle(initialValue = false)
    val provinceError by viewModel.provinceError.collectAsStateWithLifecycle(initialValue = false)

    val updateJobTitle: (String) -> Unit = {
        val errorMessage = if (it.isNotEmpty()) "" else "Job title cannot be empty!"
        viewModel.updateJobTitle(it)
        viewModel.updateJobTitleErrorMessage(errorMessage)
    }
    val updateCompanyName: (String) -> Unit = {
        val errorMessage = if (it.isNotEmpty()) "" else "Company cannot be empty!"
        viewModel.updateCompanyName(it)
        viewModel.updateCompanyNameErrorMessage(errorMessage)
    }
    val updateStreet: (String) -> Unit = {
        val errorMessage = if (it.isNotEmpty()) "" else "Street cannot be empty!"
        viewModel.updateStreet(it)
        viewModel.updateStreetErrorMessage(errorMessage)
    }
    val updateCity: (String) -> Unit = {
        val errorMessage = if (it.isNotEmpty()) "" else "City cannot be empty!"
        viewModel.updateCity(it)
        viewModel.updateCityErrorMessage(errorMessage)
    }
    val updateProvince: (String) -> Unit = {
        val errorMessage = if (it.isNotEmpty()) "" else "Province cannot be empty!"
        viewModel.updateProvince(it)
        viewModel.updateProvinceErrorMessage(errorMessage)
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.updateWorkplaceType(workplaceTypes[0])
        viewModel.updateJobType(jobTypes[0])
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        TextBodyLarge(text = "Tell us who you’re hiring")
        OutlinedTextFieldWithLabel(
            label = "Job Title*",
            value = jobTitle,
            onValueChange = updateJobTitle,
            placeholder = "Job title",
            supportingText = jobTitleErrorMessage,
            isError = jobTitleError
        )
        OutlinedTextFieldWithLabel(
            label = "Company Name*",
            value = companyName,
            onValueChange = updateCompanyName,
            placeholder = "Company name",
            supportingText = companyNameErrorMessage,
            isError = companyNameError
        )
        OutlinedTextFieldWithLabel(
            label = "Job Location*",
            value = street,
            onValueChange = updateStreet,
            placeholder = "Street or Distric",
            supportingText = streetErrorMessage,
            isError = streetError
        )
        OutlinedTextFieldWithLabel(
            placeholder = "City",
            value = city,
            onValueChange = updateCity,
            supportingText = cityErrorMessage,
            isError = cityError,
        )
        OutlinedTextFieldWithLabel(
            placeholder = "Province",
            value = province,
            onValueChange = updateProvince,
            supportingText = provinceErrorMessage,
            isError = provinceError
        )
        DropDownTextFieldWithLabel(
            label = "Workplace Type*",
            selectedOption = workplaceType,
            options = workplaceTypes,
            onSelected = { viewModel.updateWorkplaceType(workplaceTypes[it]) }
        )
        DropDownTextFieldWithLabel(
            label = "Job Type*",
            selectedOption = jobType,
            options = jobTypes,
            onSelected = { viewModel.updateJobType(jobTypes[it]) }
        )
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewAddJobGeneralSection() {
    ITTPizenTheme {
        Surface {
            AddJobGeneralSection(
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}
