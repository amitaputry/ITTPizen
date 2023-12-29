package com.ta.ittpizen.feature_profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ta.ittpizen.feature_profile.R
import com.ta.ittpizen.ui.component.text.TextBodyLarge
import com.ta.ittpizen.ui.component.text.TextBodyMedium
import com.ta.ittpizen.ui.theme.ColorText
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.PrimaryRed
import com.ta.ittpizen.ui.theme.SecondDarkGrey

@Composable
fun EditProfileHeader(
    modifier: Modifier = Modifier,
    photo: Any = "",
    onPickImageClick: () -> Unit = {},
    name: String = "",
    type: String = "",
    studentId: String = ""
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfilePhotoPicker(
            image = photo,
            onPickImageClick = onPickImageClick
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextBodyLarge(text = name, fontWeight = FontWeight.Medium, color = ColorText, textAlign = TextAlign.Center)
        TextBodyMedium(text = type, color = SecondDarkGrey, textAlign = TextAlign.Center)
        TextBodyMedium(text = studentId, color = SecondDarkGrey, textAlign = TextAlign.Center)
    }
}

@Composable
fun ProfilePhotoPicker(
    modifier: Modifier = Modifier,
    image: Any = "",
    onPickImageClick: () -> Unit = {},
) {
    val context = LocalContext.current
    Box(modifier = modifier) {
        val model = ImageRequest.Builder(context)
            .data(image)
            .crossfade(true)
            .build()
        AsyncImage(
            model = model,
            contentDescription = "profile",
            placeholder = painterResource(id = R.drawable.ic_profile_default),
            error = painterResource(id = R.drawable.ic_profile_default),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .border(1.5.dp, shape = CircleShape, color = PrimaryRed)
                .size(98.dp)
        )
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(30.dp)
                .background(Color.White)
                .clip(CircleShape)
                .clickable { onPickImageClick() }
                .padding(3.dp)
                .clip(CircleShape)
                .background(PrimaryRed)
                .align(Alignment.BottomEnd),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_edit_profile),
                contentDescription = "Edit",
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
fun PreviewEditProfileHeader() {
    ITTPizenTheme {
        Surface {
            EditProfileHeader(
                modifier = Modifier.padding(20.dp),
                name = "Amita Putry Prasasti",
                type = "Student",
                studentId = "20104014"
            )
        }
    }
}
