package com.ta.ittpizen.feature_job.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.domain.model.JobDetail
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.component.text.TextBodyLarge
import com.ta.ittpizen.ui.component.text.TextBodyMediumWithLeadingIcon
import com.ta.ittpizen.ui.theme.ColorText
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun JobDetailHeader(
    modifier: Modifier = Modifier,
    job: JobDetail
) {
    val address = buildString {
        append(job.street)
        append(", ")
        append(job.city)
        append(", ")
        append(job.province)
    }
    Column(modifier = modifier) {
        TextBodyLarge(text = job.title, fontWeight = FontWeight.Medium, color = ColorText)
        Spacer(modifier = Modifier.height(10.dp))
        TextBodyMediumWithLeadingIcon(
            leadIcon = painterResource(id = R.drawable.ic_company),
            color = ColorText,
            text = job.company,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextBodyMediumWithLeadingIcon(
            leadIcon = painterResource(id = R.drawable.ic_location),
            color = ColorText,
            text = address
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextBodyMediumWithLeadingIcon(
            leadIcon = painterResource(id = R.drawable.ic_time),
            color = ColorText,
            text = "${job.experience} Experiences"
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextBodyMediumWithLeadingIcon(
            leadIcon = painterResource(id = R.drawable.ic_graduate),
            color = ColorText,
            text = job.graduates
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextBodyMediumWithLeadingIcon(
            leadIcon = painterResource(id = R.drawable.ic_person),
            color = ColorText,
            text = job.workplaceType
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextBodyMediumWithLeadingIcon(
            leadIcon = painterResource(id = R.drawable.ic_bag),
            color = ColorText,
            text = job.jobType
        )
    }
}

@Preview
@Composable
fun PreviewJobDetailHeader() {
    ITTPizenTheme {
        Surface {
            val job = JobDetail(
                title = "Asisten Praktikum Basis Data",
                company = "Fakultas Informatika",
                street = "Jl. DI Panjaitan No.128",
                city = "Banyumas",
                province = "Jawa Tengah",
                workplaceType = "Onsite",
                jobType = "Full Time",
                description = "Membantu dosen dalam menyiapkan dan melaksanakan praktikum basis data. Membimbing dan menjawab pertanyaan mahasiswa selama praktikum. Membantu mahasiswa dalam menyelesaikan tugas praktikum. Membantu dosen dalam mengumpulkan dan menganalisis data praktikum",
                skills = listOf("Memiliki pengetahuan yang baik tentang teori dan konsep basis data", "Mampu menggunakan berbagai bahasa pemrograman basis data, seperti SQL, Python, atau Java", "Mampu menggunakan berbagai alat dan perangkat lunak basis data, seperti MySQL, PostgreSQL, atau Oracle", "Mampu berkomunikasi dan bekerja sama dengan baik", "Memiliki sikap yang positif dan proaktif"),
                experience = "0 - 1 year",
                graduates = "Minimum Diploma 3 (D3)"
            )
            JobDetailHeader(
                job = job,
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}
