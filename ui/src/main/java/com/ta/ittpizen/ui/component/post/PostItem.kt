package com.ta.ittpizen.ui.component.post

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.domain.model.post.Post
import com.ta.ittpizen.ui.theme.DisableColorGrey
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun PostItem(
    modifier: Modifier = Modifier,
    post: Post,
    enabled: Boolean = true,
    onClick: (Post) -> Unit = {},
    onProfileClick: (Post) -> Unit = {},
    onPhotoClick: (String) -> Unit = {},
    onLike: (Post) -> Unit = {},
    onComment: (Post) -> Unit = {},
    onSend: (Post) -> Unit = {},
) {
    var postLiked by rememberSaveable { mutableStateOf(post.liked) }
    var postLikes by rememberSaveable { mutableIntStateOf(post.likes) }

    val onLiked: (Post) -> Unit = {
        if (postLiked) {
            postLikes -= 1
            val updatedPost = post.copy(liked = true)
            onLike(updatedPost)
        } else {
            postLikes += 1
            val updatedPost = post.copy(liked = false)
            onLike(updatedPost)
        }
        postLiked = postLiked.not()
    }
    val outerModifier = if (enabled) Modifier
        .clickable { onClick(post) }
        .then(modifier)
    else Modifier
    Column(
        modifier = outerModifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Column(
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
                like = postLikes,
                comment = post.comments,
                liked = postLiked,
                onLike = { onLiked(post) },
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
fun PreviewPostItem() {
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
