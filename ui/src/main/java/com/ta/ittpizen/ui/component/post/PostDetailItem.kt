package com.ta.ittpizen.ui.component.post

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.domain.model.post.Post
import com.ta.ittpizen.ui.theme.DisableColorGrey
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun PostDetailItem(
    modifier: Modifier = Modifier,
    post: Post,
    onProfileClick: (Post) -> Unit = {},
    onPhotoClick: (String) -> Unit = {},
    onComment: (Post) -> Unit = {},
    onSend: (Post) -> Unit = {},
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Column(
            modifier = Modifier.animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            PostHeader(
                profile = post.user.photo,
                name = post.user.name,
                type = post.user.type,
                date = post.createdAt,
                onProfile = { onProfileClick(post) },
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            PostBody(
                text = post.text,
                media = post.media,
                onPhotoClick = onPhotoClick,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            PostFooter(
                showButton = false,
                like = post.likes,
                comment = post.comments,
                liked = post.liked,
                onLike = {  },
                onComment = { onComment(post) },
                onSend = { onSend(post) },
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)
            )
        }
        Divider(thickness = 0.5.dp, color = DisableColorGrey)
    }
}

@Preview
@Composable
fun PreviewPostDetailItem() {
    ITTPizenTheme {
        Surface {
            val postItem = Post(
//                name = "Amita Putry Prasasti",
                type = "Student",
//                date = "1 hours ago",
//                profile = "",
                text = "Haloo, salam kenal, mari saling koneksi temen-temen. Haloo, salam kenal, mari saling koneksi temen-temen. Haloo, salam kenal, mari saling koneksi temen-temen. Haloo, salam kenal, mari saling koneksi temen-temen",
                media = "https://serayunews.com/_next/image?url=https%3A%2F%2Fserayunews.pw%2Fwp-content%2Fuploads%2F2023%2F07%2FWhatsApp-Image-2023-07-02-at-14.44.51.jpeg&w=640&q=75",
                liked = true
            )
            PostDetailItem(
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
