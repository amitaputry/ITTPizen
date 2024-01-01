package com.ta.ittpizen.feature_profile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.feature_profile.R
import com.ta.ittpizen.ui.component.text.TextBodySmall
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.PrimaryRed

@Composable
fun LogoutButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .shadow(
                elevation = 2.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000)
            )
            .border(width = 1.dp, color = PrimaryRed, shape = RoundedCornerShape(size = 100.dp))
            .background(color = Color(0x80D32B28), shape = RoundedCornerShape(size = 100.dp))
            .clickable { onClick() }
            .padding(horizontal = 10.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logout),
            contentDescription = "Logout"
        )
        Spacer(modifier = Modifier.width(2.dp))
        TextBodySmall(
            text = "Logout",
            color = Color.White,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview
@Composable
fun PreviewLogoutButton() {
    ITTPizenTheme {
        Surface {
            LogoutButton(
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}
