package com.ta.ittpizen.ui.component.post

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.domain.entity.PostCommentItem
import com.ta.ittpizen.ui.theme.ITTPizenTheme

@Composable
fun PostCommentItem(
    modifier: Modifier = Modifier,
    post: PostCommentItem,
    onProfile: (PostCommentItem) -> Unit = {}
) {
    Column(
        modifier = modifier.animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        PostHeader(
            profile = post.profile,
            name = post.name,
            type = post.type,
            date = post.date,
            onProfile = { onProfile(post) }
        )
        PostBody(text = post.text)
    }
}

@Preview
@Composable
fun PreviewPostCommentItem() {
    ITTPizenTheme {
        Surface {
            val postItem = PostCommentItem(
                name = "Amita Putry Prasasti",
                type = "Student",
                date = "1 hours ago",
                profile = "",
                text = "Haloo, salam kenal, mari saling koneksi temen-temen. Haloo, salam kenal, mari saling koneksi temen-temen. Haloo, salam kenal, mari saling koneksi temen-temen. Haloo, salam kenal, mari saling koneksi temen-temen",
            )
            PostCommentItem(
                post = postItem,
                modifier = Modifier.padding(all = 20.dp)
            )
        }
    }
}
