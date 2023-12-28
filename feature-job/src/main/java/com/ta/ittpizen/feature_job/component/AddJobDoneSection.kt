package com.ta.ittpizen.feature_job.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ta.ittpizen.feature_job.R
import com.ta.ittpizen.ui.component.button.LargePrimaryButton
import com.ta.ittpizen.ui.component.text.TextBodyLarge
import com.ta.ittpizen.ui.component.textbutton.TextButton
import com.ta.ittpizen.ui.theme.ColorText
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun AddJobDoneSection(
    modifier: Modifier = Modifier,
    onSeePostClick: () -> Unit = {},
    onFinishClick: () -> Unit = {}
) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_post_done),
            contentDescription = null,
            modifier = Modifier
                .height(221.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(40.dp))
        TextBodyLarge(
            text = "Yeay, Thank you for helping students and alumni get jobs!",
            color = ColorText,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(40.dp))
        LargePrimaryButton(
            text = "See Post",
            onClick = onSeePostClick,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextButton(
            text = "Finish",
            fontSize = 14.sp,
            color = ColorText,
            onClick = onFinishClick
        )
    }
}

@Preview
@Composable
fun PreviewAddJobDoneSection() {
    ITTPizenTheme {
        AddJobDoneSection(
            modifier = Modifier.padding(20.dp)
        )
    }
}
