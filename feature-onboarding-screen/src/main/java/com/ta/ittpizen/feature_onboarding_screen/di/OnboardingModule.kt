package com.ta.ittpizen.feature_onboarding_screen.di

import com.ta.ittpizen.feature_onboarding_screen.OnboardingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val onboardingModule = module {
    viewModel { OnboardingViewModel(get()) }
}
