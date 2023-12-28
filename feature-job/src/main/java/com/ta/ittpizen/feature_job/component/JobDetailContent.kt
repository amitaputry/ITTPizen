package com.ta.ittpizen.feature_job.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.domain.entity.JobDetail
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.component.text.TextBodyLarge
import com.ta.ittpizen.ui.component.text.TextBodyMedium
import com.ta.ittpizen.ui.component.text.TextBodyMediumWithBullet
import com.ta.ittpizen.ui.theme.ColorText
import com.ta.ittpizen.ui.theme.DisableColorGrey

@Composable
fun JobDetailContent(
    modifier: Modifier = Modifier,
    job: JobDetail
) {
    Column(modifier = modifier) {
        TextBodyLarge(
            text = "Description",
            fontWeight = FontWeight.Medium,
            color = ColorText
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextBodyMedium(text = job.description, fontWeight = FontWeight.Normal)

        Spacer(modifier = Modifier.height(20.dp))
        Divider(thickness = 0.5.dp, color = DisableColorGrey)
        Spacer(modifier = Modifier.height(20.dp))

        TextBodyLarge(
            text = "Skills",
            fontWeight = FontWeight.Medium,
            color = ColorText
        )
        Spacer(modifier = Modifier.height(5.dp))
        job.skills.forEach { skill ->
            TextBodyMediumWithBullet(
                bullet = painterResource(id = R.drawable.ic_ellipse),
                text = skill,
                color = ColorText
            )
        }
    }
}