package com.ta.ittpizen.ui.component.post

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.Size
import com.ta.ittpizen.domain.entity.PostItem
import com.ta.ittpizen.ui.R
import com.ta.ittpizen.ui.component.text.TextBodySmall
import com.ta.ittpizen.ui.component.text.TextTitleSmall
import com.ta.ittpizen.ui.theme.ColorText
import com.ta.ittpizen.ui.theme.DisableColorGrey
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.SecondDarkGrey
import com.webtoonscorp.android.readmore.foundation.ToggleArea
import com.webtoonscorp.android.readmore.material3.ReadMoreText

@Composable
fun PostItem(
    modifier: Modifier = Modifier,
    post: PostItem,
    onClick: (PostItem) -> Unit = {},
    onProfile: (PostItem) -> Unit = {},
    onLike: (PostItem) -> Unit = {},
    onComment: (PostItem) -> Unit = {},
    onSend: (PostItem) -> Unit = {},
) {
    Column(
        modifier = Modifier.clickable { onClick(post) },
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            PostHeader(
                profile = post.profile,
                name = post.name,
                type = post.type,
                date = post.date,
                onProfile = { onProfile(post) }
            )
            PostBody(
                text = post.text,
                media = post.media
            )
            PostFooter(
                like = post.like,
                comment = post.comment,
                onLike = { onLike(post) },
                onComment = { onComment(post) },
                onSend = { onSend(post) },
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
        Divider(thickness = 0.5.dp, color = DisableColorGrey)
    }
}

@Composable
private fun PostHeader(
    modifier: Modifier = Modifier,
    profile: String = "",
    name: String = "",
    type: String = "",
    date: String = "",
    onProfile: () -> Unit = {}
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
                .clickable { onProfile() }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            TextTitleSmall(text = name, maxLines = 1)
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                TextBodySmall(
                    text = type,
                    color = SecondDarkGrey,
                    fontWeight = FontWeight(500)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_circle),
                    contentDescription = null
                )
                TextBodySmall(
                    text = date,
                    color = SecondDarkGrey,
                    fontWeight = FontWeight(500)
                )
            }
        }
    }
}

@Composable
private fun PostBody(
    modifier: Modifier = Modifier,
    text: String = "",
    media: String = ""
) {
    val context = LocalContext.current
    var isExpanded by remember { mutableStateOf(false) }
    Column(modifier = modifier.animateContentSize()) {
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
            readMoreMaxLines = 3,
            onExpandedChange = { isExpanded = it },
            toggleArea = ToggleArea.More
        )
        if (media.isNotEmpty()) {
            Spacer(modifier = Modifier.height(10.dp))
            val model = ImageRequest.Builder(context)
                .data(media)
//            .size(Size.ORIGINAL)
                .crossfade(true)
                .scale(Scale.FIT)
                .build()
            AsyncImage(
                model = model,
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
            )
        }
    }
}

@Composable
private fun PostLikeAndComment(
    modifier: Modifier = Modifier,
    like: Int = 0,
    comment: Int = 0
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        TextBodySmall(text = "$like Likes", color = SecondDarkGrey)
        Image(painter = painterResource(id = R.drawable.ic_circle), contentDescription = null)
        TextBodySmall(text = "$comment comments", color = SecondDarkGrey)
    }
}

@Composable
fun PostFooter(
    modifier: Modifier = Modifier,
    liked: Boolean = false,
    like: Int = 0,
    comment: Int = 0,
    onLike: () -> Unit = {},
    onComment: () -> Unit = {},
    onSend: () -> Unit = {}
) {
    val iconLike = if (liked) R.drawable.ic_like else R.drawable.ic_like
    Column(modifier = modifier) {
        PostLikeAndComment(
            like = like,
            comment = comment
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.offset(x = (-4).dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Image(
                painter = painterResource(id = iconLike),
                contentDescription = "Like",
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable { onLike() }
                    .padding(4.dp)
                    .size(24.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_comment),
                contentDescription = "Comment",
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable { onComment() }
                    .padding(4.dp)
                    .size(24.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_send),
                contentDescription = "Send",
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable { onSend() }
                    .padding(4.dp)
                    .size(24.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewPostItem() {
    ITTPizenTheme {
        Surface {
            val postItem = PostItem(
                name = "Amita Putry Prasasti",
                type = "Student",
                date = "1 hours ago",
                profile = "",
                text = "Haloo, salam kenal, mari saling koneksi temen-temen. Haloo, salam kenal, mari saling koneksi temen-temen. Haloo, salam kenal, mari saling koneksi temen-temen. Haloo, salam kenal, mari saling koneksi temen-temen",
                media = "https://kemahasiswaan.ittelkom-pwt.ac.id/wp-content/uploads/sites/27/2021/05/pilmapres20211-1200x675.png",
                liked = true
            )
            PostItem(
                post = postItem,
                modifier = Modifier.padding(
                    start = 20.dp,
                    top = 20.dp,
                    end = 20.dp
                )
            )
        }
    }
}


@Preview
@Composable
fun PreviewPostHeader() {
    ITTPizenTheme {
        Surface {
            PostHeader(
                name = "Amita Putry Prasasti",
                type = "Student",
                date = "1 hours ago"
            )
        }
    }
}

@Preview
@Composable
fun PreviewPostLikeAndComment() {
    ITTPizenTheme {
        Surface {
            PostLikeAndComment(
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewPostFooter() {
    ITTPizenTheme {
        Surface {
            PostFooter(
                modifier = Modifier.padding(16.dp)
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
