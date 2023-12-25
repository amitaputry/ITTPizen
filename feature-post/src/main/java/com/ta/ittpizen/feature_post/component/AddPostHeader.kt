package com.ta.ittpizen.feature_post.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.component.text.TextBodySmall
import com.ta.ittpizen.ui.component.text.TextTitleSmall
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.SecondDarkGrey

@Composable
fun AddPostHeader(
    modifier: Modifier = Modifier,
    profile: String = "",
    name: String = "",
    type: String = ""
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        AsyncImage(
            model = profile,
            contentDescription = name,
            contentScale = ContentScale.FillBounds,
            placeholder = painterResource(id = R.drawable.ic_profile_default),
            error = painterResource(id = R.drawable.ic_profile_default),
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            TextTitleSmall(text = name, maxLines = 1)
            Spacer(modifier = Modifier.height(2.dp))
            TextBodySmall(
                text = type,
                color = SecondDarkGrey,
                fontWeight = FontWeight(500)
            )
        }
    }
}

@Preview
@Composable
fun PreviewAddPostHeader() {
    ITTPizenTheme {
        Surface {
            AddPostHeader(
                name = "Amita Putry Prasasti",
                type = "Student",
            )
        }
    }
}
