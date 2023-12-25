package com.ta.ittpizen.ui.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun SingleChipRow(
    modifier: Modifier = Modifier,
    selectedOption: String = "",
    options: List<String> = emptyList(),
    onSelected: (String) -> Unit = {},
    contentPadding: PaddingValues = PaddingValues()
) {
    LazyRow(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .then(modifier),
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(items = options) { option ->
            val isSelected = selectedOption == option
            BaseChip(
                text = option,
                selected = isSelected,
                onSelected = onSelected
            )
        }
    }
}

@Preview
@Composable
fun PreviewSingleChipRow() {
    ITTPizenTheme {
        Surface {
            val options = listOf("All Post", "Tweet", "Academic", "#PrestasiITTP", "Events", "Scholarship")
            var selectedOption by remember { mutableStateOf(options[0]) }
            SingleChipRow(
                selectedOption = selectedOption,
                options = options,
                onSelected = { selectedOption = it },
                contentPadding = PaddingValues(16.dp)
            )
        }
    }
}
