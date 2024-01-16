package com.ta.ittpizen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ta.ittpizen.data.di.dataModule
import com.ta.ittpizen.domain.di.domainModule
import com.ta.ittpizen.feature_auth.di.authModule
import com.ta.ittpizen.feature_job.di.jobModule
import com.ta.ittpizen.feature_profile.di.profileModule
import com.ta.ittpizen.navigation.ITTPizenNavHost
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import org.koin.android.ext.koin.androidContext
import org.koin.compose.KoinApplication

@ExperimentalMaterialApi
@ExperimentalMaterialNavigationApi
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinApplication(application = {
                androidContext(applicationContext)
                modules(
                    dataModule,
                    domainModule,
                    authModule,
                    jobModule,
                    profileModule
                )
            }) {
                ITTPizenTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        ITTPizenNavHost()
                    }
                }
            }
        }
    }
}
