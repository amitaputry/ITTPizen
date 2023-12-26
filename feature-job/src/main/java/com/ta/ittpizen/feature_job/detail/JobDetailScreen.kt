package com.ta.ittpizen.feature_job.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.domain.entity.JobDetail
import com.ta.ittpizen.feature_job.component.JobDetailContent
import com.ta.ittpizen.feature_job.component.JobDetailFooter
import com.ta.ittpizen.feature_job.component.JobDetailHeader
import com.ta.ittpizen.ui.component.topappbar.DetailTopAppBar
import com.ta.ittpizen.ui.theme.DisableColorGrey
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@ExperimentalMaterial3Api
@Composable
fun JobDetailScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
    jobId: String = ""
) {
    val scrollState = rememberScrollState()

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

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = "Detail Job",
                onNavigationClick = navigateUp
            )
        },
        bottomBar = {
            JobDetailFooter()
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState)
        ) {
            JobDetailHeader(job = job, modifier = Modifier.padding(vertical = 20.dp))
            Divider(thickness = 0.5.dp, color = DisableColorGrey)
            JobDetailContent(job = job, modifier = Modifier.padding(vertical = 20.dp))
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewJobDetailScreen() {
    ITTPizenTheme {
        Surface {
            JobDetailScreen()
        }
    }
}
