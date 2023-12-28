package com.ta.ittpizen.ui.component.job

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.domain.model.JobItem
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.component.text.TextBodyLarge
import com.ta.ittpizen.ui.component.text.TextBodySmallWithLeadingIcon
import com.ta.ittpizen.ui.theme.DisableColorGrey
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalLayoutApi
@Composable
fun JobItem(
    modifier: Modifier = Modifier,
    jobItem: JobItem,
    onClick: (JobItem) -> Unit = {},
    onSaveClick: (JobItem) -> Unit = {}
) {
    Column(
        modifier = modifier
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.background)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick(jobItem) }
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        TextBodyLarge(text = jobItem.name, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(5.dp))
        JobCharacteristicsSection(characteristics = jobItem.characteristics)
        Spacer(modifier = Modifier.height(5.dp))
        TextBodySmallWithLeadingIcon(
            leadIcon = painterResource(id = R.drawable.ic_company),
            text = jobItem.company,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextBodySmallWithLeadingIcon(
            leadIcon = painterResource(id = R.drawable.ic_location),
            text = jobItem.location
        )
        Spacer(modifier = Modifier.height(5.dp))
        Divider(thickness = 0.5.dp, color = DisableColorGrey)
        Spacer(modifier = Modifier.height(10.dp))
        JobItemFooter(
            date = jobItem.date,
            saved = jobItem.saved,
            onSaveClick = { onSaveClick(jobItem) }
        )
    }
}

@ExperimentalLayoutApi
@Preview
@Composable
fun PreviewJobItem() {
    ITTPizenTheme {
        Surface {
            val job = JobItem(
                name = "Asisten Praktikum Basis Data",
                characteristics = listOf("Onsite", "Part time", "0 - 1 year", "Min D3"),
                company = "Fakultas Informatika",
                date = "12 days ago",
                location = "Jl. DI Panjaitan No.128, Banyumas, Jawa Tengah",
                saved = true
            )
            JobItem(
                jobItem = job,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            )
        }
    }
}
