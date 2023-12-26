package com.ta.ittpizen.ui.component.text

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun TextBodySmallWithLeadingIcon(
    modifier: Modifier = Modifier,
    leadIcon: Painter,
    text: String,
    fontWeight: FontWeight = FontWeight.Normal,
    size: Dp = 20.dp
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = leadIcon,
            contentDescription = null,
            modifier = Modifier.size(size)
        )
        Spacer(modifier = Modifier.width(5.dp))
        TextBodySmall(
            text = text,
            fontWeight = fontWeight,
            maxLines = 1
        )
    }
}

@Preview
@Composable
fun PreviewTextBodySmallWithLeadingIcon() {
    ITTPizenTheme {
        Surface {
            TextBodySmallWithLeadingIcon(
                leadIcon = painterResource(id = R.drawable.ic_company),
                text = "PT Graha Indonesia",
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}
