package com.ta.ittpizen.feature_post.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.ui.component.text.TextBodyLarge
import com.ta.ittpizen.ui.component.text.TextBodySmall
import com.ta.ittpizen.ui.theme.ColorText
import com.ta.ittpizen.ui.theme.SecondDarkGrey

@Composable
fun PostDetailEmptyComment(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextBodyLarge(
            text = "Well, No Comment yet :(",
            color = ColorText,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextBodySmall(
            text = "Be the first to comment on this post!",
            color = SecondDarkGrey
        )
    }
}