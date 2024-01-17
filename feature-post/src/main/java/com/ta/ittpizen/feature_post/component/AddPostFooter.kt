package com.ta.ittpizen.feature_post.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ta.ittpizen.feature_post.R
import com.ta.ittpizen.feature_post.add.AddPostType
import com.ta.ittpizen.ui.component.button.LargePrimaryButton
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import com.ta.ittpizen.ui.theme.SecondDarkGrey

@Composable
fun AddPostFooter(
    modifier: Modifier = Modifier,
    type: AddPostType = AddPostType.TWEET,
    postEnabled: Boolean = false,
    postLoading: Boolean = false,
    onAddPhotoClick: () -> Unit = {},
    onPostClick: () -> Unit = {}
) {
    val publicText = buildAnnotatedString {
        append("Anyone can see ")
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
            append("Public")
        }
    }
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = publicText,
            fontSize = 12.sp,
            color = SecondDarkGrey,
            letterSpacing = 0.05.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        if (type != AddPostType.TWEET) {
            Image(
                painter = painterResource(id = R.drawable.ic_add_photo),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable { onAddPhotoClick() }
                    .padding(4.dp)
                    .offset(y = (-2).dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
        }
        LargePrimaryButton(
            text = "Post",
            enable = postEnabled,
            loading = postLoading,
            onClick = onPostClick,
            contentPaddingValues = PaddingValues(horizontal = 32.dp, vertical = 8.dp),
        )
    }
}

@Preview
@Composable
fun PreviewAddPostFooter() {
    ITTPizenTheme {
        Surface {
            AddPostFooter(
                type = AddPostType.EVENT
            )
        }
    }
}
