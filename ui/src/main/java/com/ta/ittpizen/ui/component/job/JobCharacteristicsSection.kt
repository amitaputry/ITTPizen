package com.ta.ittpizen.ui.component.job

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalLayoutApi
@Composable
fun JobCharacteristicsSection(
    modifier: Modifier = Modifier,
    characteristics: List<String> = emptyList()
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        characteristics.forEach { characteristic ->
            JobCharacteristicItem(text = characteristic)
        }
    }
}

@ExperimentalLayoutApi
@Preview
@Composable
fun PreviewJobCharacteristicsSection() {
    ITTPizenTheme {
        Surface {
            val characteristics = listOf("Onsite", "Part time", "0 - 1 year", "Min D3")
            JobCharacteristicsSection(
                characteristics = characteristics,
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}
