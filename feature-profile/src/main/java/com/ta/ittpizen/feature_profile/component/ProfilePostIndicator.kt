package com.ta.ittpizen.feature_profile.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.component.text.TextBodyMedium
import com.ta.ittpizen.ui.theme.ColorText
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.PrimaryRed

@Composable
fun ProfilePostIndicator(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextBodyMedium(
            text = "Post",
            fontWeight = FontWeight.Medium,
            color = ColorText,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Divider(
            thickness = 1.dp,
            color = PrimaryRed,
            modifier = Modifier.width(66.dp)
        )
    }
}

@Preview
@Composable
fun PreviewProfilePostIndicator() {
    ITTPizenTheme {
        Surface {
            ProfilePostIndicator(
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}
