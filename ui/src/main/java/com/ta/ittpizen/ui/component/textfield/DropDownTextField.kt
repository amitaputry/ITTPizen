package com.ta.ittpizen.ui.component.textfield

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.component.text.TextBodyMedium
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.PrimaryRed

@Composable
fun DropDownTextField(
    modifier: Modifier = Modifier,
    selectedOption: String,
    options: List<String>,
    onSelected: (Int) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    val borderWidth = remember(key1 = expanded) {
        if (expanded) 2.dp else 1.dp
    }
    val borderColor = remember(key1 = expanded) {
        if (expanded) PrimaryRed else Color(0xFFDEDEDE)
    }
    var componentWidth by remember { mutableStateOf(0.dp) }
    val density = LocalDensity.current

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .border(
                width = borderWidth,
                color = borderColor,
                shape = RoundedCornerShape(size = 100.dp)
            )
            .clickable { expanded = true }
            .height(45.dp)
            .padding(horizontal = 12.dp, vertical = 10.dp)
            .onGloballyPositioned {
                componentWidth = with(density) {
                    it.size.width.toDp()
                }
            },
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextBodyMedium(
                text = selectedOption,
                maxLines = 1,
                modifier = Modifier.weight(1f)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "Expand"
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .padding(2.dp)
                .width(componentWidth)
        ) {
            options.forEachIndexed { index, item ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onSelected(index)
                    },
                    text = {
                        TextBodyMedium(text = item)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewDropDownTextField() {
    ITTPizenTheme {
        Surface {
            val genders = listOf("Male", "Female")
            var selectedGender by remember { mutableStateOf(genders[0]) }
            DropDownTextField(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                selectedOption = selectedGender,
                options = genders,
                onSelected = { selectedGender = genders[it] }
            )
        }
    }
}
