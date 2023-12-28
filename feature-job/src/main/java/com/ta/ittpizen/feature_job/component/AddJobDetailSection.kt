package com.ta.ittpizen.feature_job.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ta.ittpizen.feature_job.add.AddJobDetailViewModel
import com.ta.ittpizen.ui.component.text.TextBodyLarge
import com.ta.ittpizen.ui.component.text.TextTitleSmall
import com.ta.ittpizen.ui.component.textbutton.TextButton
import com.ta.ittpizen.ui.component.textfield.BaseOutlinedTextField
import com.ta.ittpizen.ui.component.textfield.DropDownTextFieldWithLabel
import com.ta.ittpizen.ui.component.textfield.OutlinedTextFieldWithLabel
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.PrimaryRed
import org.koin.androidx.compose.koinViewModel

@ExperimentalMaterial3Api
@Composable
fun AddJobDetailSection(
    modifier: Modifier = Modifier,
    viewModel: AddJobDetailViewModel = koinViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val experiences = listOf("Full Time", "Part Time", "Contract", "Volunteer", "Internship")
    val graduates = listOf("0 - 1 year", "1 - 2 year", "3 - 4 year")

    val description = uiState.description
    val skills = uiState.skills
    val experience = uiState.experience
    val graduate = uiState.graduate
    val linkApplication = uiState.linkApplication

    val descriptionErrorMessage = uiState.descriptionErrorMessage

    val jobTitleError by viewModel.descriptionError.collectAsStateWithLifecycle(initialValue = false)

    val updateJobTitle: (String) -> Unit = {
        val errorMessage = if (it.isNotEmpty()) "" else "Description cannot be empty!"
        viewModel.updateDescription(it)
        viewModel.updateDescriptionErrorMessage(errorMessage)
    }

    val updateSkill: (Int, String) -> Unit = { index, skill ->
        val mutableSkills = skills.toMutableList()
        mutableSkills[index] = skill
        viewModel.updateSkills(mutableSkills)
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.updateExperience(experiences[0])
        viewModel.updateGraduate(graduates[0])
    }

    Column(
        modifier = modifier.animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        TextBodyLarge(text = "Almost done! Add job criteria")
        OutlinedTextFieldWithLabel(
            label = "Description*",
            value = description,
            onValueChange = updateJobTitle,
            supportingText = descriptionErrorMessage,
            isError = jobTitleError,
            minLines = 4,
            singleLine = false
        )
        Column(modifier = Modifier.animateContentSize()) {
            TextTitleSmall(text = "Skills", color = Color(0xFF343433))
            Spacer(modifier = Modifier.height(4.dp))
            skills.forEachIndexed { index, skill ->
                BaseOutlinedTextField(
                    value = skill,
                    onValueChange = { updateSkill(index, it) },
                    placeholder = "Skill",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            TextButton(
                text = "+ ADD OTHER SKILLS",
                fontSize = 12.sp,
                color = PrimaryRed,
                onClick = viewModel::addSkill
            )
        }
        DropDownTextFieldWithLabel(
            label = "Experience*",
            selectedOption = experience,
            options = experiences,
            onSelected = { viewModel.updateExperience(experiences[it]) }
        )
        DropDownTextFieldWithLabel(
            label = "Graduates*",
            selectedOption = graduate,
            options = graduates,
            onSelected = { viewModel.updateGraduate(graduates[it]) }
        )
        OutlinedTextFieldWithLabel(
            label = "Link Application",
            value = linkApplication,
            onValueChange = viewModel::updateLinkApplication,
            placeholder = "https://.."
        )
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewAddJobDetailSection() {
    ITTPizenTheme {
        Surface {
            AddJobDetailSection(
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}
