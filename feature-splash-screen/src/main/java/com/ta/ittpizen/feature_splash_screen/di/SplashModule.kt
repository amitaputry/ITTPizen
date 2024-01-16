package com.ta.ittpizen.feature_splash_screen.di

import com.ta.ittpizen.feature_splash_screen.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {
    viewModel { SplashViewModel(get()) }
}
