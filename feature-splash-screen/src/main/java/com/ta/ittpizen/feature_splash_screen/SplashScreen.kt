package com.ta.ittpizen.feature_splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ta.ittpizen.ui.theme.ITTPizenTheme
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = koinViewModel(),
    navigateToOnboardingScreen: () -> Unit = {},
    navigateToLoginScreen: () -> Unit = {},
    navigateToMainScreen: () -> Unit = {}
) {
    val isFirstOpen by viewModel.isFirstOpen.collectAsStateWithLifecycle(initialValue = false)
    val isLogin by viewModel.isLogin.collectAsStateWithLifecycle(initialValue = false)

    LaunchedEffect(key1 = Unit) {
        delay(2000L)
        if (isFirstOpen) {
            navigateToOnboardingScreen()
            return@LaunchedEffect
        }
        if (isLogin) {
            navigateToMainScreen()
            return@LaunchedEffect
        }
        navigateToLoginScreen()
    }
    Scaffold(modifier = modifier) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_ittpizen_logo),
                contentDescription = "ITTPizen Logo",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(203.dp)
                    .height(59.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewSplashScreen() {
    ITTPizenTheme {
        Surface {
            SplashScreen()
        }
    }
}
