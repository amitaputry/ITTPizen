package com.ta.ittpizen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ta.ittpizen.domain.entity.PostItem
import com.ta.ittpizen.feature_auth.di.authModule
import com.ta.ittpizen.navigation.ITTPizenNavHost
import com.ta.ittpizen.ui.component.post.PostItem
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import org.koin.compose.KoinApplication

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
        }
    }
}
