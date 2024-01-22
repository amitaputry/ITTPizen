package com.ta.ittpizen.ui.component.post

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.ta.ittpizen.common.encode
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.theme.ColorText
import com.ta.ittpizen.ui.theme.DisableColorGrey
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.webtoonscorp.android.readmore.foundation.ToggleArea
import com.webtoonscorp.android.readmore.material3.ReadMoreText

@Composable
fun PostBody(
    modifier: Modifier = Modifier,
    text: String = "",
    media: String = "",
    onPhotoClick: (String) -> Unit = {}
) {
    val context = LocalContext.current
    var isExpanded by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        ReadMoreText(
            text = text,
            expanded = isExpanded,
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
            lineHeight = 16.sp,
            color = ColorText,
            letterSpacing = 0.05.sp,
            readMoreText = "Read More",
            readMoreFontSize = 12.sp,
            readMoreColor = DisableColorGrey,
            readMoreMaxLines = 8,
            onExpandedChange = { isExpanded = it },
            toggleArea = ToggleArea.More
        )
        if (media.isNotEmpty()) {
            Spacer(modifier = Modifier.height(10.dp))
            val model = ImageRequest.Builder(context)
                .data(media)
//                .size(Size.ORIGINAL)
                .crossfade(true)
                .scale(Scale.FIT)
                .placeholder(R.drawable.img_media_placeholder)
                .error(R.drawable.img_media_placeholder)
                .memoryCacheKey(media)
                .build()
            AsyncImage(
                model = model,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .height(211.dp)
                    .fillMaxWidth()
                    .clickable { onPhotoClick(media.encode()) }
            )
        }
    }
}

@Preview
@Composable
fun PreviewPostBody() {
    ITTPizenTheme {
        Surface {
            PostBody(
                text = "Let’s connect, sharing information and job with students, alumni, lecturer and other civitas at ITTPizen now. Let’s connect, sharing information and job with students, alumni, lecturer and other civitas at ITTPizen now. Let’s connect, sharing information and job with students, alumni, lecturer and other civitas at ITTPizen now.",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }
    }
}

