package com.ta.ittpizen.ui.component.searchbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.component.text.TextBodyMedium
import com.ta.ittpizen.ui.theme.GreyShadow
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.SecondDarkGrey

@Composable
fun DummySearchBar(
    modifier: Modifier = Modifier,
    placeholder: String = "",
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .height(42.dp)
            .fillMaxWidth()
            .background(GreyShadow)
            .clickable { onClick() }
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = R.drawable.ic_search), contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        TextBodyMedium(text = placeholder, color = SecondDarkGrey)
    }
}

@Preview
@Composable
fun PreviewDummySearchBar() {
    ITTPizenTheme {
        Surface {
            DummySearchBar(
                placeholder = "Find your friends..",
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}
