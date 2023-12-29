package com.ta.ittpizen.feature_photo_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.smarttoolfactory.zoom.enhancedZoom
import com.smarttoolfactory.zoom.rememberEnhancedZoomState
import com.ta.ittpizen.common.decode
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.component.topappbar.DetailTransparentTopAppBar
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun PhotoDetailScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
    photo: String = ""
) {
    val context = LocalContext.current
    val enhancedZoomState = rememberEnhancedZoomState(imageSize = IntSize.Zero)
    val model = ImageRequest.Builder(context)
        .data(photo.decode())
        .crossfade(true)
        .build()
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        AsyncImage(
            model = model,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.img_media_placeholder),
            error = painterResource(id = R.drawable.img_media_placeholder),
            modifier = Modifier
                .fillMaxSize()
                .enhancedZoom(enhancedZoomState = enhancedZoomState)
        )
        DetailTransparentTopAppBar(
            navigateUp = navigateUp
        )
    }
}

@Preview
@Composable
fun PreviewPhotoDetailScreen() {
    ITTPizenTheme {
        Surface {
            PhotoDetailScreen(
                photo = "https://static.promediateknologi.id/crop/0x0:0x0/750x500/webp/photo/ayosemarang/images/post/articles/2021/04/14/75145/kuliah-gratis-institut-teknologi-telkom-purwokerto.jpg"
            )
        }
    }
}
