package com.ta.ittpizen.ui.component.topappbar

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.component.iconbutton.BaseIconButton
import com.ta.ittpizen.ui.component.text.TextBodyLarge
import com.ta.ittpizen.ui.component.text.TextBodySmall
import com.ta.ittpizen.ui.theme.ColorText
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.SecondDarkGrey

@Composable
fun ChatTopAppBar(
    modifier: Modifier = Modifier,
    photo: String = "",
    name: String = "",
    type: String = "",
    onNavigateClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .shadow(
                elevation = 4.dp,
                spotColor = Color(0x40AAAAAA),
                ambientColor = Color(0x40AAAAAA)
            )
            .fillMaxWidth()
            .padding(all = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        BaseIconButton(
            icon = painterResource(id = R.drawable.ic_back),
            contentDescription = "Back",
            onClick = onNavigateClick
        )
        AsyncImage(
            model = photo,
            placeholder = painterResource(id = R.drawable.ic_profile_default),
            error = painterResource(id = R.drawable.ic_profile_default),
            contentDescription = name,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
            TextBodyLarge(
                text = name,
                fontWeight = FontWeight.Medium,
                color = ColorText,
                maxLines = 1
            )
            TextBodySmall(text = type, color = SecondDarkGrey)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun PreviewChatTopAppBar() {
    ITTPizenTheme {
        Surface {
            Scaffold(
                topBar = {
                    ChatTopAppBar(
                        name = "Amita Putry Prasasti",
                        type = "Student",
                    )
                }
            ) {}
        }
    }
}
