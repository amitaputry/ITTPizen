package com.ta.ittpizen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ta.ittpizen.feature_auth.di.authModule
import com.ta.ittpizen.feature_chat.chat.ChatScreen
import com.ta.ittpizen.feature_post.add.AddPostScreen
import com.ta.ittpizen.feature_post.add.AddPostType
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import org.koin.compose.KoinApplication

@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinApplication(application = {
                modules(authModule)
            }) {
                ITTPizenTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
//                        ITTPizenNavHost()
                        AddPostScreen(
                            type = AddPostType.ACHIEVEMENT
                        )
                    }
                }
            }
        }
    }
}
